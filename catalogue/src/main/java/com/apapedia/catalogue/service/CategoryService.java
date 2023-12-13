package com.apapedia.catalogue.service;

import com.apapedia.catalogue.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    List<String> getAllCategoryName();

    Category getCategoryByName(String name);

    Category save(Category category);
}
