package com.apapedia.catalogue.controller;

import java.util.List;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.service.CatalogueService;
import com.apapedia.catalogue.service.DTO.CatalogueMapper;
import com.apapedia.catalogue.service.DTO.UpdateCatalogueDTO;

@RestController
@RequestMapping("/api")
public class CatalogueController {
    @Autowired
    private CatalogueService catalogueService;

    @Autowired
    private CatalogueMapper catalogueMapper;

    @GetMapping(value = "/catalogue/view-all")
    public List<Catalogue> getAllCatalogue() {
        return catalogueService.getAllCatalogue();
    }

    @GetMapping(value = "/catalogue/view-catalogue-by-id")
    public Catalogue getCatalogueById(UUID id) {
        return catalogueService.getCatalogueById(id);
    }

    @PutMapping(value = "/catalogue/update-catalogue")
    public Catalogue updateCatalogue(UpdateCatalogueDTO catalogueDTO) {
        var catalogue = catalogueMapper.updateCatalogueDTOToCatalogue(catalogueDTO);
        return catalogueService.updateCatalogue(catalogue, catalogueDTO.getId());
    }
}
