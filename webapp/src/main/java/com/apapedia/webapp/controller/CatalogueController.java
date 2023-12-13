package com.apapedia.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Base64;
import java.util.UUID;

import com.apapedia.webapp.dto.CatalogueMapper;
import com.apapedia.webapp.dto.request.CreateCatalogueDTO;
import com.apapedia.webapp.dto.request.UpdateCatalogueDTO;
import com.apapedia.webapp.restservice.CatalogueRestService;
import com.apapedia.webapp.restservice.CategoryRestService;

import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/catalogue/view-all")
    public String viewAllCatalogue(Model model){
        var listCatalogue = catalogueRestService.viewAllCatalogue();
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
