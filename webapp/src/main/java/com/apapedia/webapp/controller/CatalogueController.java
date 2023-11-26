package com.apapedia.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogueController {
    
    @GetMapping("/catalogue/view-all")
    public String viewAllCatalogue() {
        return "view-all-catalogue";
    }
}
