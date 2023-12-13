package com.apapedia.catalogue.controller;

import com.apapedia.catalogue.dto.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.dto.request.UpdateCatalogueRequestDTO;
import com.apapedia.catalogue.service.CatalogueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.UUID;

@RestController
@RequestMapping("/api/catalogue")
public class CatalogueController {
    @Autowired
    private CatalogueService catalogueService;


    @GetMapping(value = "/view-all")
    public ResponseEntity<?> getAllCatalogue() {
        try{
            var catalogue = catalogueService.getAllCatalogue();
            return ResponseEntity.ok(catalogue);
        } catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/image/{catalogId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@PathVariable(value = "catalogId") UUID catalogId) {
        try {
            var imageData = catalogueService.getImageByCatalogeId(catalogId);
            return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .body(imageData);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCatalogueById(@PathVariable("id") UUID id) {
        try {
            var catalogue = catalogueService.getCatalogueById(id);
            return ResponseEntity.ok(catalogue);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateCatalogue(@RequestBody UpdateCatalogueRequestDTO catalogueDTO) {
        try {
            var catalogue = catalogueService.updateCatalogue(catalogueDTO);
            return ResponseEntity.ok(catalogue);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/seller")
    public ResponseEntity<?> getCataloguesBySellerId(@RequestParam(value="sellerId", required = false) UUID sellerId) {
        try {
            var catalogues = catalogueService.getCataloguesBySellerId(sellerId);
            return ResponseEntity.ok(catalogues);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addCatalogue(
        @RequestParam UUID sellerId,
        @RequestParam int price,
        @RequestParam String productName,
        @RequestParam String productDescription,
        @RequestParam int stock,
        @RequestParam MultipartFile imageFile,
        @RequestParam UUID categoryId) {
        try {
            var newCatalogueDTO = new CreateCatalogueRequestDTO();
            newCatalogueDTO.setSellerId(sellerId);
            newCatalogueDTO.setPrice(price);
            newCatalogueDTO.setProductName(productName);
            newCatalogueDTO.setProductDescription(productDescription);
            newCatalogueDTO.setStock(stock);
            newCatalogueDTO.setImageFile(imageFile);
            newCatalogueDTO.setCategoryId(categoryId);
            var catalogue = catalogueService.createCatalogue(newCatalogueDTO);
            return ResponseEntity.ok(catalogue);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCatalogue(@PathVariable ("id") UUID id){
        try{
            catalogueService.deleteCatalogue(id);
            return ResponseEntity.ok("Catalogue with id " + id + " has been deleted");
        } catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/product")
    public ResponseEntity<?> getCatalogueByName(@RequestParam (value="productName", required=false) String productName){
        try{
            var catalogue = catalogueService.getCatalogueByName(productName);
            return ResponseEntity.ok(catalogue);
        } catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/price")
    public ResponseEntity<?>getCatalogueByPrice(@RequestParam (value="price", required=false) int price){
        try{
            var catalogue = catalogueService.getCatalogListByPrice(price);
            return ResponseEntity.ok(catalogue);
        } catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

//    @GetMapping("catalogue/sortBy/{sortBy}/sortOrder/{sortOrder}")
//    public List<Catalogue> getCatalogueListSorted(@PathVariable ("sortBy")String sortBy, @PathVariable ("sortOrder") String sortOrder){
//        return catalogueService.getCatalogListSorted(sortBy, sortOrder);
//    }

    @GetMapping("/sort")
    public ResponseEntity<?> getCatalogueListSorted(@RequestParam (value="sortCriteria", required = false) String sortCriteria) {
        try {
            String[] sortParams = sortCriteria.split("-");
            String sortBy = sortParams[0];
            String sortOrder = sortParams[1];
            var catalogue = catalogueService.getCatalogListSorted(sortBy, sortOrder);
            return ResponseEntity.ok(catalogue);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
