package com.apapedia.webapp.restservice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.apapedia.webapp.model.Catalogue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CatalogueRestServiceImpl implements CatalogueRestService{

    @Override
    public List<Map<String, Object>> viewAllCatalogue() throws IOException, InterruptedException{
        //Send Request To Get JSON Data Catalogue
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://apap-103.cs.ui.ac.id/api/catalogue/view-all"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //Convert JSON Data Catalogue To Map
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> catalogueList = objectMapper.readValue(response.body(), new TypeReference<List<Map<String, Object>>>(){});

        return catalogueList;
    }

    @Override
    public Catalogue getCatalogueById(String id) throws IOException, InterruptedException{
        //Send Request To Get JSON Data Catalogue
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://api.apapedia.com/catalogue/get-by-id/" + id))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //Convert JSON Data Catalogue To Map
        ObjectMapper objectMapper = new ObjectMapper();
        Catalogue catalogue = objectMapper.readValue(response.body(), Catalogue.class);

        return catalogue;
    }
}
