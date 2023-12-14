package com.apapedia.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.apapedia.webapp.dto.request.CreateCatalogueDTO;
import com.apapedia.webapp.dto.request.UpdateCatalogueDTO;
import com.apapedia.webapp.model.Seller;
import com.apapedia.webapp.restservice.CatalogueRestService;
import com.apapedia.webapp.restservice.CategoryRestService;
import com.apapedia.webapp.restservice.SellerRestService;
import com.apapedia.webapp.security.jwt.JwtUtils;
import com.apapedia.webapp.utils.utils.ImageUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatalogueController {

    @Autowired
    private CatalogueRestService catalogueRestService;

    @Autowired
    private CategoryRestService categoryRestService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SellerRestService sellerRestService;

    @GetMapping("/catalogue/add-catalogue")
    public String addCatalogueForm(Model model) {
        model.addAttribute("catalogue", new CreateCatalogueDTO());
        model.addAttribute("listCategory", categoryRestService.viewAllCategory());
        return "add-catalogue";
    }

    @PostMapping("/catalogue/add-catalogue")
    public String addCatalogue(@ModelAttribute CreateCatalogueDTO catalogueDTO, BindingResult bindingResult,
            @RequestParam("image") MultipartFile imageBase64, RedirectAttributes redirectAttrs, HttpServletRequest request) {
        try {
            HttpSession httpSession = request.getSession();
            var token = httpSession.getAttribute("token");
            String sellerId = jwtUtils.getClaimsFromJwtToken(token.toString()).substring(8, 44);
            var seller = sellerRestService.readSeller(UUID.fromString(sellerId), token.toString());
            catalogueDTO.setSellerId(UUID.fromString(sellerId));
            // Create a CatalogDTO instance and set the image byte array
            catalogueDTO.setImage(imageBase64);

            // Call the service to add the catalog
            catalogueRestService.createCatalogue(catalogueDTO);

            redirectAttrs
                    .addFlashAttribute("success",
                            String.format("Catalogue %s has been created", catalogueDTO.getProductName()));

            return "redirect:/catalogue/view-all";
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
            return "error-page"; // Redirect to an error page or appropriate handling
        }
    }

    @GetMapping("/catalogue/view-all")
    public String viewAllCatalogues(Model model) {

        String baseUrl = "http://localhost:8081" + "/api/catalogue/view-all";
        ResponseEntity<List<Map<String, Object>>> cataloguesResponse = new RestTemplate().exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                });

        List<Map<String, Object>> listCatalogue = cataloguesResponse.getBody();

        // Konversi byte array ke Base64-encoded string di model
        listCatalogue.forEach(catalogue -> {
            // Konversi image ke byte array
            byte[] imageBytes = catalogue.get("image") instanceof String
                    ? Base64.getDecoder().decode((String) catalogue.get("image"))
                    : (byte[]) catalogue.get("image");

            try {
                imageBytes = ImageUtils.decompressImage(imageBytes);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while downloading image");
            }

            catalogue.put("imageBase64", Base64.getEncoder().encodeToString(imageBytes));
        });

        model.addAttribute("listCatalogue", listCatalogue);

        return "view-all-catalogue";
    }

    @GetMapping("/catalogue/view-detail/{id}")
    public String viewDetailCatalogue(@PathVariable("id") UUID id, Model model) {
        var catalogue = catalogueRestService.getCatalogueById(id);
        model.addAttribute("catalogue", catalogue);
        return "view-detail-catalogue";
    }

    @GetMapping("/catalogue/update/{id}")
    public String updateCatalogueForm(@PathVariable("id") UUID id, Model model) {
        var catalogue = catalogueRestService.getCatalogueById(id);
        model.addAttribute("catalogueDTO", catalogue);
        model.addAttribute("listCategory", categoryRestService.viewAllCategory());
        return "update-catalogue";
    }

    @PostMapping("catalogue/update")
    public String updateCatalogue(@ModelAttribute UpdateCatalogueDTO catalogueDTO, Model model,
            @RequestParam("image") MultipartFile imageBase64, RedirectAttributes redirectAttrs,HttpServletRequest request) {

        try {
            // Create a CatalogDTO instance and set the image byte array
            catalogueDTO.setImage(imageBase64);

            // Call the service to add the catalog
            catalogueRestService.updateCatalogue(catalogueDTO);

            redirectAttrs
                    .addFlashAttribute("success",
                            String.format("Catalogue %s has been created", catalogueDTO.getProductName()));

            return "redirect:/catalogue/view-all";
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
            return "error-page"; // Redirect to an error page or appropriate handling
        }
    }
}
