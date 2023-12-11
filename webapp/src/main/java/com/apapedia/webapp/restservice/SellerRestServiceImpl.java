package com.apapedia.webapp.restservice;

import org.springframework.stereotype.Service;


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
