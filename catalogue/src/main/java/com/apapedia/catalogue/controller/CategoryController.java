package com.apapedia.catalogue.controller;

import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CategoryDb;
import com.apapedia.catalogue.service.CatalogueService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryDb categoryDb;

    @Autowired
    private CatalogueService catalogueService;


    @GetMapping("/category/view-all")
    public List<Category> getAllCategory(){
        return categoryDb.findAll();
    }

    @PostMapping(value="/category/create")
    public Category addCategory(@Valid @RequestBody Category categoryDTO, BindingResult bindingResult){
        
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            var category = catalogueService.createCategory(categoryDTO);
            return category; 
        }
    }
}
