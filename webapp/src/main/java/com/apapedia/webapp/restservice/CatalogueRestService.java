package com.apapedia.webapp.restservice;

import java.util.List;
import java.util.UUID;

import com.apapedia.webapp.dto.request.CategoryDTO;
import com.apapedia.webapp.dto.request.CreateCatalogueDTO;
import com.apapedia.webapp.model.Catalogue;

public interface CatalogueRestService {
    Catalogue createCatalogue(CreateCatalogueDTO catalogue);
    List<Catalogue> viewAllCatalogue();
    Catalogue getCatalogueById(UUID id);
    Catalogue updateCatalogue(Catalogue catalogue, UUID id);
    CategoryDTO getCategoryById(UUID id);
    List<Catalogue> getCatalogueByName(String productName);
}
