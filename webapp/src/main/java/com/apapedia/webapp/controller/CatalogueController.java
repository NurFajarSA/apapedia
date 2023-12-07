package com.apapedia.webapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.UUID;

import com.apapedia.webapp.restservice.CatalogueRestService;

@Controller
public class CatalogueController {

    @Autowired
    private CatalogueRestService catalogueRestService;
    
    @GetMapping("/catalogue/view-all")
    public String viewAllCatalogue(Model model) throws IOException, InterruptedException{
        var listCatalogue = catalogueRestService.viewAllCatalogue();
        model.addAttribute("listCatalogue", listCatalogue);
        return "view-all-catalogue";
    }

    @GetMapping("/catalogue/get-catalogue-by-id")
    public String viewDetailCatalogue(@ModelAttribute("id") UUID id, Model model) throws IOException, InterruptedException{
        var catalogue = catalogueRestService.getCatalogueById(id);
        model.addAttribute("catalogue", catalogue);
        return "view-detail-catalogue";
    }
}
