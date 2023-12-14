package com.apapedia.webapp.restservice;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.apapedia.webapp.dto.request.CategoryDTO;
import com.apapedia.webapp.dto.request.CreateCatalogueDTO;
import com.apapedia.webapp.model.Catalogue;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CatalogueRestServiceImpl implements CatalogueRestService{

    @Override
    public Catalogue createCatalogue(CreateCatalogueDTO catalogueDTO){

        Mono<Catalogue> catalogueMono = WebClient.create()
            .post()
            .uri("http://103.41.205.41:10103/api/catalogue/add-catalogue")
            .body(BodyInserters.fromValue(catalogueDTO))
            .retrieve()
            .bodyToMono(Catalogue.class);
        var response = catalogueMono.block();
        return response;
    }

    @Override
    public List<Catalogue> viewAllCatalogue() {
        Flux<Catalogue> catalogueFlux = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/catalogue/view-all")
            .retrieve()
            .bodyToFlux(Catalogue.class);
        var listCatalogue = catalogueFlux.collectList().block();

        return listCatalogue;
    }

    @Override
    public Catalogue getCatalogueById(UUID id){
        Mono<Catalogue> catalogueMono = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/catalogue/view-catalogue-by-id/" + id)
            .retrieve()
            .bodyToMono(Catalogue.class);
        return catalogueMono.block();
    }

    @Override
    public List<Catalogue> getCatalogueByName(String productName) {
        Flux<Catalogue> catalogueFlux = WebClient.create()
                .get()
                .uri("http://sonsulung.com:10103/api/catalogue/productName/" + productName)
                .retrieve()
                .bodyToFlux(Catalogue.class);
        var listCatalogue = catalogueFlux.collectList().block();

        return listCatalogue;
    }

    @Override
    public List<Catalogue> getCatalogueByPrice(int price) {
        Flux<Catalogue> catalogueFlux = WebClient.create()
                .get()
                .uri("http://sonsulung.com:10103/api/catalogue/price/" + price)
                .retrieve()
                .bodyToFlux(Catalogue.class);
        var listCatalogue = catalogueFlux.collectList().block();

        return listCatalogue;
    }

    @Override
    public List<Catalogue> getCatalogueByListSorted(String sortCriteria) {
        Flux<Catalogue> catalogueFlux = WebClient.create()
                .get()
                .uri("http://sonsulung.com:10103/api/catalogue/sorted/" + sortCriteria)
                .retrieve()
                .bodyToFlux(Catalogue.class);
        var listCatalogue = catalogueFlux.collectList().block();

        return listCatalogue;
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
