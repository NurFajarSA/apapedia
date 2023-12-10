package com.apapedia.catalogue.service;
import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.service.DTO.NewCatalogueDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public interface CatalogueService {


    List<Catalogue> getCatalogListSorted(String sortBy, String sortOrder);
    List<Catalogue> getCatalogListByPrice(int price);
    List<Catalogue> getCatalogListByName (String productName);

    List<Catalogue> getAllCatalogue();
    Catalogue getCatalogueById(UUID id);
    Catalogue updateCatalogue(Catalogue catalogue, UUID id);
    List<Catalogue> getCataloguesBySellerId(UUID idSeller);
    Catalogue saveCatalogue(Catalogue catalogue);
    Catalogue createCatalogue(NewCatalogueDTO catalogueDTO);

    void deleteCatalogue(Catalogue catalogue);

    byte[] cekFile(MultipartFile file) throws IOException;

    }
