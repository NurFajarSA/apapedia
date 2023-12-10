package com.apapedia.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import com.apapedia.webapp.DTO.CatalogueMapper;
import com.apapedia.webapp.DTO.CreateCatalogueDTO;
import com.apapedia.webapp.DTO.UpdateCatalogueDTO;
import com.apapedia.webapp.model.Catalogue;
import com.apapedia.webapp.model.Category;
import com.apapedia.webapp.restservice.CatalogueRestService;
import com.apapedia.webapp.restservice.CategoryRestService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
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
    public String addCatalogue(@ModelAttribute CreateCatalogueDTO catalogueDTO, BindingResult bindingResult, @RequestPart("image") MultipartFile image, Model model, RedirectAttributes redirectAttrs){
        var catalogueFromDTO = new Catalogue();
        catalogueFromDTO.setProductName(catalogueDTO.getProductName());
        catalogueFromDTO.setPrice(catalogueDTO.getPrice());
        catalogueFromDTO.setProductDescription(catalogueDTO.getProductDescription());
        catalogueFromDTO.setStock(catalogueDTO.getStock());
        catalogueFromDTO.setImageBase64(catalogueDTO.getImage());

        List<Category> listCategory = categoryRestService.viewAllCategory();
        for(Category category : listCategory){
            if(category.getId().equals(UUID.fromString(catalogueDTO.getCategory()))){
                catalogueFromDTO.setCategory(category);
            }
        }

         if (!image.isEmpty()) {
            try {
                // Convert the uploaded image to a Base64 String
                byte[] imageBytes = image.getBytes();
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                catalogueDTO.setImage(imageBase64);

            } catch (IOException e) {
                // Handle the exception appropriately
                bindingResult.rejectValue("image", "upload.error");
            }
        } else {
            // Handle the case when no image is uploaded
            bindingResult.rejectValue("image", "upload.empty");
        }

        var catalogue = catalogueRestService.createCatalogue(catalogueFromDTO);
        model.addAttribute("catalogueName", catalogue.getProductName());
        redirectAttrs.addFlashAttribute("success",
                String.format("Produk baru %s berhasil disimpan", catalogue.getProductName()));
        return "redirect:/catalogue/view-all";
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
        return "update-catalogue";
    }

    @PutMapping("catalogue/update")
    public String updateCatalogue(@ModelAttribute UpdateCatalogueDTO catalogueDTO, Model model) {
        var catalogueFromDTO = catalogueMapper.updateCatalogueDTOToCatalogue(catalogueDTO);
        var catalogue = catalogueRestService.updateCatalogue(catalogueFromDTO, catalogueDTO.getId());
        model.addAttribute("catalogueName", catalogue.getProductName());
        return "update-catalogue-success";
    }
}
