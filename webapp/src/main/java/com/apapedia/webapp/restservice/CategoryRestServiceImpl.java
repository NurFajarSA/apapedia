package com.apapedia.webapp.restservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.apapedia.webapp.dto.request.CategoryDTO;

import reactor.core.publisher.Flux;

@Service
public class CategoryRestServiceImpl implements CategoryRestService{
    @Override
    public List<CategoryDTO> viewAllCategory(){
        Flux<CategoryDTO> categoryFlux = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/category/view-all")
            .retrieve()
            .bodyToFlux(CategoryDTO.class);
        return categoryFlux.collectList().block();
    }
}
