package com.apapedia.catalogue.service;
import com.apapedia.catalogue.model.Catalogue;

import java.util.List;

public interface CatalogueService {


    List<Catalogue> getCatalogListSorted(String sortBy, String sortOrder);
    List<Catalogue> getCatalogListByPrice(int price);
    List<Catalogue> getCatalogListByName (String productName);


    }
