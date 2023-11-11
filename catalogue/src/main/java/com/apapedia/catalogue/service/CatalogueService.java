package com.apapedia.catalogue.service;

import java.util.List;
import java.util.UUID;

import com.apapedia.catalogue.model.Catalogue;

public interface CatalogueService {
    List<Catalogue> getAllCatalogue();
    Catalogue getCatalogueById(UUID id);
    Catalogue updateCatalogue(Catalogue catalogue, UUID id);
    void deleteCatalogue(Catalogue catalogue);
}
