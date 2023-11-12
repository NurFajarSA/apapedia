package com.apapedia.catalogue.controller;

import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CategoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryDb categoryDb;


    @GetMapping("/category/view-all")
    public List<Category> getAllCategory(){
        return categoryDb.findAll();
    }
}
