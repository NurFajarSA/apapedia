package com.apapedia.catalogue.controller;


import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogueController{

    @Autowired
    private CatalogueService catalogueService;

    @GetMapping("/catalogue/productName/{productName}")
    public List<Catalogue> getCatalogueByName(@PathVariable ("productName") String productName){
        return catalogueService.getCatalogListByName(productName);
    }

    @GetMapping("catalogue/price/{price}")
    public List<Catalogue> getCatalogueByPrice(@PathVariable ("price") int price){
        return catalogueService.getCatalogListByPrice(price);

    }

//    @GetMapping("catalogue/sortBy/{sortBy}/sortOrder/{sortOrder}")
//    public List<Catalogue> getCatalogueListSorted(@PathVariable ("sortBy")String sortBy, @PathVariable ("sortOrder") String sortOrder){
//        return catalogueService.getCatalogListSorted(sortBy, sortOrder);
//    }

    @GetMapping("/catalogue/sorted/{sortCriteria}")
    public List<Catalogue> getCatalogueListSorted(@PathVariable ("sortCriteria") String sortCriteria) {
        String[] sortParams = sortCriteria.split("-");
        String sortBy = sortParams[0];
        String sortOrder = sortParams[1];

        return catalogueService.getCatalogListSorted(sortBy, sortOrder);
    }



}
