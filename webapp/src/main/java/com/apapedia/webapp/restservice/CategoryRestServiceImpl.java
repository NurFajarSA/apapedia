package com.apapedia.webapp.restservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.apapedia.webapp.model.Category;

import reactor.core.publisher.Flux;

@Service
public class CategoryRestServiceImpl implements CategoryRestService{
    @Override
    public List<Category> viewAllCategory(){
        Flux<Category> categoryFlux = WebClient.create()
            .get()
            .uri("https://apap-103.cs.ui.ac.id/api/category/view-all")
            .retrieve()
            .bodyToFlux(Category.class);
        return categoryFlux.collectList().block();
    }
}
