package com.apapedia.webapp.restservice;

import java.util.List;

import com.apapedia.webapp.dto.request.CategoryDTO;

public interface CategoryRestService {
    List<CategoryDTO> viewAllCategory();
}
