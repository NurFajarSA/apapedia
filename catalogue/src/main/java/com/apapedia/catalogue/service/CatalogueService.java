package com.apapedia.catalogue.service;
import com.apapedia.catalogue.model.Catalogue;

import java.util.List;
import java.util.UUID;


public interface CatalogueService {


    List<Catalogue> getCatalogListSorted(String sortBy, String sortOrder);
    List<Catalogue> getCatalogListByPrice(int price);
    List<Catalogue> getCatalogListByName (String productName);

    List<Catalogue> getAllCatalogue();
    Catalogue getCatalogueById(UUID id);
    Catalogue updateCatalogue(Catalogue catalogue, UUID id);
    List<Catalogue> getCataloguesBySellerId(UUID idSeller);
    Catalogue addCatalogue(Catalogue catalogue);

    void deleteCatalogue(Catalogue catalogue);

    }
