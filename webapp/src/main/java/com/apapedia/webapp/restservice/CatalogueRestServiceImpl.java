package com.apapedia.webapp.restservice;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.apapedia.webapp.model.Catalogue;
import com.apapedia.webapp.model.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CatalogueRestServiceImpl implements CatalogueRestService{

    @Override
    public Catalogue createCatalogue(Catalogue catalogue){
        Mono<Catalogue> catalogueMono = WebClient.create()
            .post()
            .uri("https://apap-103.cs.ui.ac.id/api/catalogue/add-catalogue")
            .body(Mono.just(catalogue), Catalogue.class)
            .retrieve()
            .bodyToMono(Catalogue.class);
        return catalogueMono.block();
    }

    @Override
    public List<Catalogue> viewAllCatalogue() {
        Flux<Catalogue> catalogueFlux = WebClient.create()
            .get()
            .uri("https://apap-103.cs.ui.ac.id/api/catalogue/view-all")
            .retrieve()
            .bodyToFlux(Catalogue.class);
        return catalogueFlux.collectList().block();
    }

    @Override
    public Catalogue getCatalogueById(UUID id){
        Mono<Catalogue> catalogueMono = WebClient.create()
            .get()
            .uri("https://apap-103.cs.ui.ac.id/api/catalogue/view-catalogue-by-id/" + id)
            .retrieve()
            .bodyToMono(Catalogue.class);
        return catalogueMono.block();
    }

    @Override
    public Category getCategoryById(UUID id) {
        Flux<Category> categoryFlux = WebClient.create()
            .get()
            .uri("https://apap-103.cs.ui.ac.id/api/category/view-all")
            .retrieve()
            .bodyToFlux(Category.class);
        for(Category category : categoryFlux.collectList().block()){
            if(category.getId().equals(id)){
                return category;
            }
        }
        return null;
    }

    @Override
    public Catalogue updateCatalogue(Catalogue catalogueDTO, UUID id){
        var category = getCategoryById(catalogueDTO.getCategory().getId());
        catalogueDTO.setCategory(category);
        return catalogueDTO;
    }
}
