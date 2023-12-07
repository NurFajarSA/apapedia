package com.apapedia.webapp.restservice;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.apapedia.webapp.model.Catalogue;

public interface CatalogueRestService {
    List<Map<String, Object>> viewAllCatalogue() throws IOException, InterruptedException;
    Catalogue getCatalogueById(UUID id) throws IOException, InterruptedException;
}
