package com.apapedia.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

import com.apapedia.webapp.DTO.CatalogueMapper;
import com.apapedia.webapp.DTO.CreateCatalogueDTO;
import com.apapedia.webapp.DTO.UpdateCatalogueDTO;
import com.apapedia.webapp.restservice.CatalogueRestService;
import com.apapedia.webapp.restservice.CategoryRestService;

import org.springframework.web.bind.annotation.PutMapping;

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
    public String addCatalogue(@ModelAttribute CreateCatalogueDTO catalogueDTO, Model model){
        var catalogueFromDTO = catalogueMapper.createCatalogueDTOToCatalogue(catalogueDTO);
        var catalogue = catalogueRestService.createCatalogue(catalogueFromDTO);
        model.addAttribute("catalogueName", catalogue.getProductName());
        return "add-catalogue-success";
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
