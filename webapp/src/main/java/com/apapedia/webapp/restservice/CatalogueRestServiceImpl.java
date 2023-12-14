package com.apapedia.webapp.restservice;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.apapedia.webapp.dto.request.CategoryDTO;
import com.apapedia.webapp.dto.request.CreateCatalogueDTO;
import com.apapedia.webapp.dto.request.UpdateCatalogueDTO;
import com.apapedia.webapp.dto.response.CatalogueResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CatalogueRestServiceImpl implements CatalogueRestService{

    @Override
    public CreateCatalogueDTO createCatalogue(CreateCatalogueDTO catalogueDTO){

        Mono<String> catalogueMono = WebClient.create()
            .post()
            .uri("http://sonsulung.com:10103/api/catalogue/add"
            )
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(getMultipartFormData(catalogueDTO))
            .retrieve()
            .bodyToMono(String.class);
        var response = catalogueMono.block();
        return catalogueDTO;
    }

    private BodyInserter<?, ? super ClientHttpRequest> getMultipartFormData(CreateCatalogueDTO catalogueDTO) {
        // ubah semua data jadi multipart/form-data
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("productName", catalogueDTO.getProductName());
        builder.part("productDescription", catalogueDTO.getProductDescription());
        builder.part("categoryId", catalogueDTO.getCategory().toString());
        builder.part("price", catalogueDTO.getPrice());
        builder.part("stock", catalogueDTO.getStock());
        builder.part("sellerId", catalogueDTO.getSellerId().toString());
        // tambahkan juga image);
        builder.part("imageFile", catalogueDTO.getImage().getResource());
        return BodyInserters.fromMultipartData(builder.build());
    }

    @Override
    public List<CatalogueResponseDTO> viewAllCatalogue() {
        Flux<CatalogueResponseDTO> catalogueFlux = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/catalogue/view-all")
            .retrieve()
            .bodyToFlux(CatalogueResponseDTO.class);
        var listCatalogue = catalogueFlux.collectList().block();

        return listCatalogue;
    }

    @Override
    public MultipartFile getImage(UUID id){
        Mono<MultipartFile> imageMono = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/catalogue/get-image/" + id)
            .retrieve()
            .bodyToMono(MultipartFile.class);
        return imageMono.block();
    }

    @Override
    public CatalogueResponseDTO getCatalogueById(UUID id){
        Mono<CatalogueResponseDTO> catalogueMono = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/catalogue/" + id)
            .retrieve()
            .bodyToMono(CatalogueResponseDTO.class);
        return catalogueMono.block();
    }

    @Override
    public UpdateCatalogueDTO getCatalogueByIdUpdate(UUID id){
        Mono<UpdateCatalogueDTO> catalogueMono = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/catalogue/" + id)
            .retrieve()
            .bodyToMono(UpdateCatalogueDTO.class);
        return catalogueMono.block();
    }

    @Override
    public CategoryDTO getCategoryById(UUID id) {
        Flux<CategoryDTO> categoryFlux = WebClient.create()
            .get()
            .uri("http://sonsulung.com:10103/api/category/view-all")
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
    public UpdateCatalogueDTO updateCatalogue(UpdateCatalogueDTO catalogueDTO){
        Mono<UpdateCatalogueDTO> catalogueMono = WebClient.create()
            .put()
            .uri("http://sonsulung.com:10103/api/catalogue/update")
            .body(BodyInserters.fromValue(catalogueDTO))
            .retrieve()
            .bodyToMono(UpdateCatalogueDTO.class);
        return catalogueMono.block();
    }
}
