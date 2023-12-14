package com.apapedia.webapp.restservice;

import com.apapedia.webapp.dto.request.UpdateProfileRequestDTO;
import com.apapedia.webapp.model.Seller;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
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
        Seller seller = sellerMono.block();
        return seller;
    }
    @Override
    public Seller updateSeller(UUID sellerId, UpdateProfileRequestDTO updateProfileRequestDTO, String token) {
            Mono<Seller> sellerMono = WebClient.create()
            .put()
            .uri("http://103.41.205.41:10102/api/user/update")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .body(BodyInserters.fromValue(updateProfileRequestDTO))
            .retrieve()
            .bodyToMono(Seller.class);

        var seller = sellerMono.block();
        return seller;
    }




}
