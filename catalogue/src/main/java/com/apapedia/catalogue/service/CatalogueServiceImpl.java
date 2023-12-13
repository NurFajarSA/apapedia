package com.apapedia.catalogue.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.catalogue.dto.CatalogueMapper;
import com.apapedia.catalogue.dto.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.dto.request.UpdateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.repository.CatalogueDb;
import com.apapedia.catalogue.repository.CategoryDb;
import com.apapedia.catalogue.utils.ImageUtils;

@Service
public class CatalogueServiceImpl implements CatalogueService{

    @Autowired
    private CatalogueDb catalogueDb;

    @Autowired
    private CategoryDb categoryDb;

    @Autowired
    private CatalogueMapper catalogueMapper;



    @Override
    public Catalogue createCatalogue(CreateCatalogueRequestDTO catalogueDTO) {
        try{
            var catalogue = catalogueMapper.createCatalogueRequestToCatalogue(catalogueDTO);
            var category = categoryDb.findById(catalogueDTO.getCategoryId()).get();
            catalogue.setCategory(category);
            var image = ImageUtils.compressImage(catalogueDTO.getImageFile().getBytes());
            catalogue.setImage(image);
            return catalogueDb.save(catalogue);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while uploading image");
        }
    }

    @Override
    public byte[] getImageByCatalogeId(UUID catalogueId) {
        try{
            var catalogue = catalogueDb.findById(catalogueId).get();
            return ImageUtils.decompressImage(catalogue.getImage());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while downloading image");
        }
    }

    @Override
    public Catalogue updateCatalogue(UpdateCatalogueRequestDTO catalogueDTO) {
        var catalogue = catalogueMapper.updateCatalogueRequestToCatalogue(catalogueDTO);
        var category = categoryDb.findById(catalogueDTO.getCategoryId()).get();
        var catalogueOri = catalogueDb.findById(catalogueDTO.getId()).get();
        catalogue.setImage(catalogueOri.getImage());
        catalogue.setCategory(category);
        return catalogueDb.save(catalogue);
    }

    @Override
    public void deleteCatalogue(UUID id) {
        catalogueDb.deleteById(id);
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
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sortBy or sortOrder");
    }

    @Override
    public List<Catalogue> getCatalogListByPrice(int price) {
        return catalogueDb.findByPrice(price);
    }

    @Override
    public List<Catalogue> getCataloguesBySellerId(UUID idSeller) {
        return catalogueDb.findBySellerIdOrderByProductNameAsc(idSeller);
    }

    @Override
    public List<Catalogue> getAllCatalogue() {
        return catalogueDb.findAll();
    }

    @Override
    public Catalogue getCatalogueById(UUID id) {
        return catalogueDb.findById(id).get();
    }

    @Override
    public Catalogue getCatalogueByName(String productName) {
        return catalogueDb.findByProductName(productName);
    }
    
}
