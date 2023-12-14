package com.apapedia.catalogue.controller;

import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CategoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryDb categoryDb;


    @GetMapping("/view-all")
    public ResponseEntity<?> getAllCategory(){
        try{
            List<Category> category = categoryDb.findAll();
            return ResponseEntity.ok(category);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
