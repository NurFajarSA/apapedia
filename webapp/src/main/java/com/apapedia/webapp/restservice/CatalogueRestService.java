package com.apapedia.webapp.restservice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.apapedia.webapp.model.Catalogue;

public interface CatalogueRestService {
    List<Map<String, Object>> viewAllCatalogue() throws IOException, InterruptedException;
    Catalogue getCatalogueById(String id) throws IOException, InterruptedException;
}
