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
            .uri("http://103.41.205.41:10103/api/category/view-all")
            .retrieve()
            .bodyToFlux(Category.class);
        return categoryFlux.collectList().block();
    }
}
