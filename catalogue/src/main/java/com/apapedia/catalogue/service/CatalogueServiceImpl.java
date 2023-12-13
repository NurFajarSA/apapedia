package com.apapedia.catalogue.service;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CatalogueDb;
import com.apapedia.catalogue.repository.CategoryDb;
import com.apapedia.catalogue.DTO.NewCatalogueDTO;
import com.apapedia.catalogue.DTO.UpdateCatalogueDTO;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;



@Service
public class CatalogueServiceImpl implements CatalogueService{

    @Autowired
    private CatalogueDb catalogueDb;

    @Autowired
    private CategoryDb categoryDb;

    @Override
    public List<Catalogue> getCatalogListByName (String productName){
        return catalogueDb.findByProductName(productName);
    }

    @Override
    public List<Catalogue> getCatalogListByPrice(int price){
        return catalogueDb.findByPrice(price);
    }

    @Override
    public List<Catalogue> getCatalogListSorted(String sortBy, String sortOrder) {
        if ("price".equalsIgnoreCase(sortBy)) {
            if ("asc".equalsIgnoreCase(sortOrder)) {
                return catalogueDb.findAllByOrderByPriceAsc();
            } else if("desc".equalsIgnoreCase(sortOrder)){
                return catalogueDb.findAllByOrderByPriceDesc();
            }
        }
        else if ("productName".equalsIgnoreCase(sortBy)) {
            if ("asc".equalsIgnoreCase(sortOrder)) {
                return catalogueDb.findAllByOrderByProductNameAsc();
            } else if ("desc".equalsIgnoreCase(sortOrder)){
                return catalogueDb.findAllByOrderByProductNameDesc();
            }
        }

        return null;
    }

    @Transactional
    public List<Catalogue> getAllCatalogue() {

        return catalogueDb.findAllByOrderByProductNameByAsc();
    }
    
    @Override
    public Catalogue getCatalogueById(UUID id) {
        return catalogueDb.findById(id).orElse(null);
    }

    @Override
    public Catalogue updateCatalogue(UpdateCatalogueDTO catalogueDTO, UUID id){
        Catalogue catalogue = getCatalogueById(id);
        System.out.println(catalogue.getId());
        System.out.println(catalogue.getProductName());
        System.out.println(catalogue.getPrice());
        System.out.println(catalogue.getProductDescription());
        System.out.println(catalogue.getStock());
        System.out.println(catalogue.getCategory().getName());
        if(catalogue != null){
            var category = new Category();
            catalogue.setProductName(catalogueDTO.getProductName());
            catalogue.setPrice(catalogueDTO.getPrice());
            catalogue.setProductDescription(catalogueDTO.getProductDescription());
            catalogue.setStock(catalogueDTO.getStock());
            catalogue.setImage(catalogueDTO.getImage());
            category.setId(catalogueDTO.getCategory().getId());
            category.setName(catalogueDTO.getCategory().getName());
            categoryDb.save(category);
            catalogue.setCategory(category);
            catalogueDb.save(catalogue);
        }
        return catalogue;
    }

    @Override
    public List<Catalogue> getCataloguesBySellerId(UUID idSeller) {
    return catalogueDb.findByIdSellerOrderByProductNameAsc(idSeller);
    }

    @Override
    public Catalogue createCatalogue(NewCatalogueDTO catalogueDTO){
            Catalogue catalogue = new Catalogue();
            catalogue.setProductName(catalogueDTO.getProductName());
            catalogue.setPrice(catalogueDTO.getPrice());
            catalogue.setProductDescription(catalogueDTO.getProductDescription());
            catalogue.setCategory(categoryDb.findById(catalogueDTO.getCategory()).get());
            catalogue.setStock(catalogueDTO.getStock());
            catalogue.setImage(catalogueDTO.getImage());
            catalogueDb.save(catalogue);
            return catalogue;
        
    }
    
    public void deleteCatalogue(Catalogue catalogue) {
        catalogueDb.delete(catalogue);
    }
}
