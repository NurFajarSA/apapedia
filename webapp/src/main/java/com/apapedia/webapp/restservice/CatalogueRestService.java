package com.apapedia.webapp.restservice;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.apapedia.webapp.dto.request.CategoryDTO;
import com.apapedia.webapp.dto.request.CreateCatalogueDTO;
import com.apapedia.webapp.dto.request.UpdateCatalogueDTO;
import com.apapedia.webapp.dto.response.CatalogueResponseDTO;

public interface CatalogueRestService {
    CreateCatalogueDTO createCatalogue(CreateCatalogueDTO catalogue);
    List<CatalogueResponseDTO> viewAllCatalogue();
    CatalogueResponseDTO getCatalogueById(UUID id);
    UpdateCatalogueDTO updateCatalogue(UpdateCatalogueDTO catalogue);
    UpdateCatalogueDTO getCatalogueByIdUpdate(UUID id);
    CategoryDTO getCategoryById(UUID id);
    MultipartFile getImage(UUID id);
    void deleteCatalogue(UUID id);

    List<CatalogueResponseDTO> getCatalogueByName(String productName);
    List<CatalogueResponseDTO> getCatalogueByPrice(int price);
    List<CatalogueResponseDTO> getCatalogueByListSorted(String sortCriteria);
}
