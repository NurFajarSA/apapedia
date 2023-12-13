package com.apapedia.catalogue.service;

import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.repository.CatalogueDb;
import com.apapedia.catalogue.repository.CategoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDb categoryDb;

    @Override
    public List<Category> getAllCategory(){
        return categoryDb.findAll();
    }

    @Override
    public List<String> getAllCategoryName(){
        List<Category> listCategory = getAllCategory();
        List<String> listCategoryName = new ArrayList<>();
        for (Category category : listCategory) {
            listCategoryName.add(category.getName());
        }
        return listCategoryName;
    }

    @Override
    public Category getCategoryByName(String name){
        return categoryDb.findByName(name);
    }
}
