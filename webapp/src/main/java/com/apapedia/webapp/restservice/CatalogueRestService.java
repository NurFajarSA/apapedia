package com.apapedia.webapp.restservice;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.apapedia.webapp.model.Catalogue;
import com.apapedia.webapp.model.Category;

public interface CatalogueRestService {
    Catalogue createCatalogue(Catalogue catalogue);
    List<Catalogue> viewAllCatalogue();
    Catalogue getCatalogueById(UUID id);
    Catalogue updateCatalogue(Catalogue catalogue, UUID id);
    Category getCategoryById(UUID id);
}
