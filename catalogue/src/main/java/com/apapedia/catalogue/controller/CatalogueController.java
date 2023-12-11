package com.apapedia.catalogue.controller;


import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.apapedia.catalogue.service.DTO.CatalogueMapper;
import com.apapedia.catalogue.service.DTO.NewCatalogueDTO;
import com.apapedia.catalogue.service.DTO.UpdateCatalogueDTO;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @GetMapping(value = "/catalogue/image/{catalogId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImage(@PathVariable(value = "catalogId") UUID catalogId) {
        var catalog = catalogueService.getCatalogueById(catalogId);
        return new ByteArrayResource(catalog.getImage());
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

    @GetMapping(value = "/catalogue/view-catalogue-by-seller-id")
    public List<Catalogue> getCataloguesBySellerId(@RequestParam UUID idSeller) {
        return catalogueService.getCataloguesBySellerId(idSeller);
    }

    @PostMapping(value = "/catalogue/add-catalogue")
    public Catalogue addCatalogue(@Valid @RequestBody NewCatalogueDTO newCatalogueDTO, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }else{
                var catalogueSave = catalogueService.createCatalogue(newCatalogueDTO);
                return catalogueSave;
        }
        
    }

    @DeleteMapping(value = "/catalogue/{id}")
    public String deleteCatalogue(@PathVariable ("id") UUID id){
        var catalogue = catalogueService.getCatalogueById(id);
        catalogueService.deleteCatalogue(catalogue);

        return "Catalogue with id: " + id + " has been deleted";
    }

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
