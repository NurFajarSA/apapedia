package com.apapedia.webapp.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.apapedia.webapp.dto.request.UpdateCatalogueDTO;
import com.apapedia.webapp.model.Catalogue;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {

    CatalogueMapper INSTANCE = Mappers.getMapper(CatalogueMapper.class);
    Catalogue updateCatalogueDTOToCatalogue(UpdateCatalogueDTO catalogueDTO);

    @Mapping(source = "category.name", target = "category")
    UpdateCatalogueDTO catalogueToUpdateCatalogueDTO(Catalogue catalogue);

    // @AfterMapping
    // default void afterMapping(@MappingTarget UpdateCatalogueDTO catalogueDTO, Catalogue catalogue) {
    //     // Your custom logic after the basic mapping
    //     // For example, directly set String category as name in CategoryDTO
    //     catalogueDTO.setCategory(catalogue.getCategory().getName());
    // }
}
