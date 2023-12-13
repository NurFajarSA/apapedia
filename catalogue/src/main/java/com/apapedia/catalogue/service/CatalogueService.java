package com.apapedia.catalogue.service;
import com.apapedia.catalogue.dto.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.dto.request.UpdateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;

import java.util.List;
import java.util.UUID;

public interface CatalogueService {
    Catalogue createCatalogue(CreateCatalogueRequestDTO catalogueDTO);
    Catalogue updateCatalogue(UpdateCatalogueRequestDTO catalogue);
    void deleteCatalogue(UUID id);
    List<Catalogue> getCatalogListSorted(String sortBy, String sortOrder);
    List<Catalogue> getCatalogListByPrice(int price);
    List<Catalogue> getCataloguesBySellerId(UUID idSeller);
    List<Catalogue> getAllCatalogue();
    Catalogue getCatalogueById(UUID id);
    Catalogue getCatalogueByName(String productName);
    byte[] getImageByCatalogeId(UUID catalogueId);

    
}
