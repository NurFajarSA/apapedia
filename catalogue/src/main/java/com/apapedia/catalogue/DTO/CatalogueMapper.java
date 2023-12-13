package com.apapedia.catalogue.dto;

import org.mapstruct.Mapper;

import com.apapedia.catalogue.dto.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.dto.request.UpdateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {
    Catalogue createCatalogueRequestToCatalogue(CreateCatalogueRequestDTO createCatalogueRequestDTO);
    Catalogue updateCatalogueRequestToCatalogue(UpdateCatalogueRequestDTO updateCatalogueRequestDTO);
}
