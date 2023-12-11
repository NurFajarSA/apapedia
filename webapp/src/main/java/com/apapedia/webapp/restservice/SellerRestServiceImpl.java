package com.apapedia.webapp.restservice;

import com.apapedia.webapp.model.Seller;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class SellerRestServiceImpl implements SellerRestService {
    @Override
    public Seller readSeller(UUID sellerId, String token) {
        Mono<Seller> sellerFlux = WebClient.create()
                .get()
                .uri("https://apap-102.cs.ui.ac.id/api/user/" + sellerId.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                .retrieve()
                .bodyToMono(Seller.class);
        var response = sellerFlux.block();
        return response;

    }
}
