package com.apapedia.webapp.restservice;

import com.apapedia.webapp.DTO.SellerResponseDTO;
import com.apapedia.webapp.model.Catalogue;
import com.apapedia.webapp.model.Seller;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class SellerRestServiceImpl implements SellerRestService {
    @Override
    public Seller readSeller(UUID sellerId, String token) {
        Mono<Seller> sellerMono = WebClient.create()
                .get()
                .uri("http://103.41.205.41:10102/api/user/" + sellerId.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                .retrieve()
                .bodyToMono(Seller.class);
        var response = sellerMono.block();
        return response;

    }

    @Override
    public Seller withdraw(UUID sellerId, String token, long withdraw) {
        Mono<Seller> sellerMono = WebClient.create()
                .put()
                .uri("http://103.41.205.41:10102/api/user/" + sellerId.toString() +"/withdraw?amount=" + withdraw)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                .retrieve()
                .bodyToMono(Seller.class);
        System.out.println(sellerMono);
        Seller seller = sellerMono.block();
//        long currentBalance = seller.getBalance();
//        if(currentBalance >= withdraw){
//            long newBalance = currentBalance - withdraw;
//            seller.setBalance(newBalance);
//            WebClient.create()
//                    .put()
//                    .uri("http://103.41.205.41:10102/api/user/update")
//                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                    .bodyValue(seller)
//                    .retrieve()
//                    .bodyToMono(Seller.class)
//                    .block();
        return seller;
    }




}
