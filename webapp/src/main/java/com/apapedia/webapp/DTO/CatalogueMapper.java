package com.apapedia.webapp.DTO;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.apapedia.webapp.DTO.request.UpdateCatalogueDTO;
import com.apapedia.webapp.model.Catalogue;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {

    CatalogueMapper INSTANCE = Mappers.getMapper(CatalogueMapper.class);
    Catalogue updateCatalogueDTOToCatalogue(UpdateCatalogueDTO catalogueDTO);

    @Mapping(source = "category.name", target = "category")
    UpdateCatalogueDTO catalogueToUpdateCatalogueDTO(Catalogue catalogue);

    CategoryDTO map(String value);

    @AfterMapping
    default void afterMapping(@MappingTarget UpdateCatalogueDTO catalogueDTO, Catalogue catalogue) {
        // Your custom logic after the basic mapping
        // For example, directly set String category as name in CategoryDTO
        catalogueDTO.setCategory(catalogue.getCategory().getName());
    }
}
