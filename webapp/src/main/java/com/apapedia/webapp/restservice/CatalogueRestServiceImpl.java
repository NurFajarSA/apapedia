package com.apapedia.webapp.restservice;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.apapedia.webapp.DTO.request.CategoryDTO;
import com.apapedia.webapp.DTO.request.CreateCatalogueDTO;
import com.apapedia.webapp.model.Catalogue;
import com.apapedia.webapp.model.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CatalogueRestServiceImpl implements CatalogueRestService{

    @Override
    public Catalogue createCatalogue(CreateCatalogueDTO catalogueDTO){

        Mono<Catalogue> catalogueMono = WebClient.create()
            .post()
            .uri("http://103.41.205.41:10103/api/catalogue/add-catalogue")
            .body(BodyInserters.fromValue(catalogue))
            .retrieve()
            .bodyToMono(Catalogue.class);
        var response = catalogueMono.block();
        return response;
    }

    @Override
    public List<Catalogue> viewAllCatalogue() {
        Flux<Catalogue> catalogueFlux = WebClient.create()
            .get()
            .uri("http://103.41.205.41:10103/api/catalogue/view-all")
            .retrieve()
            .bodyToFlux(Catalogue.class);
        var listCatalogue = catalogueFlux.collectList().block();

        return listCatalogue;
    }

    @Override
    public Catalogue getCatalogueById(UUID id){
        Mono<Catalogue> catalogueMono = WebClient.create()
            .get()
            .uri("http://103.41.205.41:10103/api/catalogue/view-catalogue-by-id/" + id)
            .retrieve()
            .bodyToMono(Catalogue.class);
        return catalogueMono.block();
    }

    @Override
    public CategoryDTO getCategoryById(UUID id) {
        Flux<CategoryDTO> categoryFlux = WebClient.create()
            .get()
            .uri("http://103.41.205.41:10103/api/category/view-all")
            .retrieve()
            .bodyToFlux(CategoryDTO.class);
        for(CategoryDTO category : categoryFlux.collectList().block()){
            if(category.getId().equals(id)){
                return category;
            }
        }
        return null;
    }

    @Override
    public Catalogue updateCatalogue(Catalogue catalogueDTO, UUID id){
        Mono<Catalogue> catalogueMono = WebClient.create()
            .put()
            .uri("http://localhost:8081/api/catalogue/update-catalogue/" + id)
            .body(BodyInserters.fromValue(catalogueDTO))
            .retrieve()
            .bodyToMono(Catalogue.class);
        return catalogueMono.block();
    }
}
