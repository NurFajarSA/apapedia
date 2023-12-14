package com.apapedia.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.apapedia.webapp.utils.ImageUtils;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import java.util.List;

import com.apapedia.webapp.dto.CatalogueMapper;
import com.apapedia.webapp.dto.request.CreateCatalogueDTO;
import com.apapedia.webapp.dto.request.UpdateCatalogueDTO;
import com.apapedia.webapp.restservice.CatalogueRestService;
import com.apapedia.webapp.restservice.CategoryRestService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatalogueController {

    @Autowired
    private CatalogueRestService catalogueRestService;

    @Autowired
    private CatalogueMapper catalogueMapper;

    @Autowired
    private CategoryRestService categoryRestService;

    @GetMapping("/catalogue/add-catalogue")
    public String addCatalogueForm(Model model){
        model.addAttribute("catalogue", new CreateCatalogueDTO());
        model.addAttribute("listCategory", categoryRestService.viewAllCategory());
        return "add-catalogue";
    }

    @PostMapping("/catalogue/add-catalogue")
    public String addCatalogue(@ModelAttribute CreateCatalogueDTO catalogueDTO, BindingResult bindingResult, @RequestParam("image") String imageBase64, RedirectAttributes redirectAttrs){
        try {
            // Decode base64 string to byte array
            byte[] imageByteArray = Base64.getDecoder().decode(imageBase64);

            // Create a CatalogDTO instance and set the image byte array
            catalogueDTO.setImage(imageByteArray);

            // Call the service to add the catalog
            catalogueRestService.createCatalogue(catalogueDTO);

            redirectAttrs
                .addFlashAttribute("success", String.format("Catalogue %s has been created", catalogueDTO.getProductName()));

            return "redirect:/catalogue/view-all";
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
            return "error-page"; // Redirect to an error page or appropriate handling
        }
    }
    
    // @GetMapping("/catalogue/view-all")
    // public String viewAllCatalogue(Model model){
    //     var listCatalogue = catalogueRestService.viewAllCatalogue();
    //     model.addAttribute("listCatalogue", listCatalogue);
    //     return "view-all-catalogue";
    // }

    @GetMapping("/catalogue/view-all")
    public String viewAllCatalogues(Model model) {

        String baseUrl = "http://sonsulung.com:10103"+ "/api/catalogue/view-all";
        ResponseEntity<List<Map<String, Object>>> cataloguesResponse = new RestTemplate().exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );
    
        List<Map<String, Object>> listCatalogue = cataloguesResponse.getBody();
    
        // Konversi byte array ke Base64-encoded string di model
        listCatalogue.forEach(catalogue -> {
            // Konversi image ke byte array
            byte[] imageBytes = catalogue.get("image") instanceof String ?
                    Base64.getDecoder().decode((String) catalogue.get("image")) :
                    (byte[]) catalogue.get("image");

                    try {
                        imageBytes = ImageUtils.decompressImage(imageBytes);
                    } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while downloading image");
        }
    
            catalogue.put("imageBase64", Base64.getEncoder().encodeToString(imageBytes));
        });

        model.addAttribute("listCatalogue", listCatalogue);

        return "view-all-catalogue";
    }


    @GetMapping("/catalogue/view-detail/{id}")
    public String viewDetailCatalogue(@PathVariable("id") UUID id, Model model){
        var catalogue = catalogueRestService.getCatalogueById(id);
        model.addAttribute("catalogue", catalogue);
        return "view-detail-catalogue";
    }

    @GetMapping("/catalogue/update/{id}")
    public String updateCatalogueForm(@PathVariable("id") UUID id, Model model){
        var catalogue = catalogueRestService.getCatalogueById(id);

        var catalogueDTO = catalogueMapper.catalogueToUpdateCatalogueDTO(catalogue);
        model.addAttribute("catalogueDTO", catalogueDTO);
        model.addAttribute("listCategory", categoryRestService.viewAllCategory());
        return "update-catalogue";
    }

    @PostMapping("catalogue/update")
    public String updateCatalogue(@ModelAttribute UpdateCatalogueDTO catalogueDTO, Model model, @RequestParam("image") String imageBase64, RedirectAttributes redirectAttrs) {

        try {
            // Decode base64 string to byte array
            byte[] imageByteArray = Base64.getDecoder().decode(imageBase64);

            // Create a CatalogDTO instance and set the image byte array
            catalogueDTO.setImage(imageByteArray);
            //Map the DTO to the Catalogue model
            var catalogue = catalogueMapper.updateCatalogueDTOToCatalogue(catalogueDTO);
            // Call the service to add the catalog
            catalogueRestService.updateCatalogue(catalogue, catalogueDTO.getId());

            redirectAttrs
                .addFlashAttribute("success", String.format("Catalogue %s has been update", catalogue.getProductName()));

            return "redirect:/catalogue/view-all";
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
            return "error-page"; // Redirect to an error page or appropriate handling
        }
    }
}
