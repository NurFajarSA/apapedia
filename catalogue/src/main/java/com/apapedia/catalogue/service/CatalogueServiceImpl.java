package com.apapedia.catalogue.service;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.repository.CatalogueDb;
import com.apapedia.catalogue.service.DTO.NewCatalogueDTO;

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
    public List<Catalogue> getCataloguesBySellerId(UUID idSeller) {
    return catalogueDb.findByIdSellerOrderByProductNameAsc(idSeller);
    }

    @Override
    public Catalogue saveCatalogue(Catalogue catalogue) {
        return catalogueDb.save(catalogue);
    }

    @Override
    public Catalogue createCatalogue(NewCatalogueDTO catalogueDTO) {
        Catalogue catalogue = new Catalogue();
        catalogue.setProductName(catalogueDTO.getProductName());
        catalogue.setPrice(catalogueDTO.getPrice());
        catalogue.setProductDescription(catalogueDTO.getProductDescription());
        catalogue.setCategory(catalogueDTO.getCategory());
        catalogue.setStock(catalogueDTO.getStock());
        catalogue.setImage(catalogueDTO.getImage());
        catalogueDb.save(catalogue);
        return catalogue;
    }
    
    public void deleteCatalogue(Catalogue catalogue) {
        catalogueDb.delete(catalogue);
    }

    @Override
    public byte[] cekFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        
        if (!isImage(file)) {
            throw new IllegalArgumentException("File is not an image");
        }


        return file.getBytes();
    }


    private boolean isImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png"));
    }
}
