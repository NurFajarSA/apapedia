package com.apapedia.catalogue.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.repository.CatalogueDb;

@Service
public class CatalogueServiceImpl implements CatalogueService{
    @Autowired
    private CatalogueDb catalogueDb;

    @Override
    public List<Catalogue> getAllCatalogue() {
        return catalogueDb.findAllByOrderByProductNameByAsc();
    }
    
    @Override
    public Catalogue getCatalogueById(UUID id) {
        for (Catalogue catalogue : getAllCatalogue()) {
            if (catalogue.getId().equals(id)) {
                return catalogue;
            }
        }
        return null;
    }

    @Override
    public Catalogue updateCatalogue(Catalogue catalogueDTO, UUID id){
        Catalogue catalogue = getCatalogueById(id);
        if(catalogue != null){
            catalogue.setProductName(catalogueDTO.getProductName());
            catalogue.setPrice(catalogueDTO.getPrice());
            catalogue.setProductDescription(catalogueDTO.getProductDescription());
            catalogue.setIdCategory(catalogueDTO.getIdCategory());
            catalogueDb.save(catalogue);
        }
        return catalogue;
    }

    @Override
    public void deleteCatalogue(Catalogue catalogue) {
        catalogueDb.delete(catalogue);
    }
}
