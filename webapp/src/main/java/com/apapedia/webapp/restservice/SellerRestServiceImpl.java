package com.apapedia.webapp.restservice;

import com.apapedia.webapp.model.Seller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SellerRestServiceImpl implements SellerRestService {


//    @Override
//    public Seller getSeller(Seller seller){
//        //Send Request To Get JSON Data Catalogue
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://api.apapedia.com/{id}/"))
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        //Convert JSON Data Catalogue To Map
//        ObjectMapper objectMapper = new ObjectMapper();
//        Seller seller = objectMapper.readValue(response.body(), Seller.class);
//
//        return seller;
//
//    }
}
