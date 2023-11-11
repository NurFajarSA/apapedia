package com.apapedia.catalogue.service.DTO;

import org.mapstruct.Mapper;

import com.apapedia.catalogue.model.Catalogue;

@Mapper(componentModel = "spring")

public interface CatalogueMapper {
    Catalogue updateCatalogueDTOToCatalogue(UpdateCatalogueDTO updateCatalogueDTO);
    UpdateCatalogueDTO catalogueToUpdateCatalogueDTO(Catalogue catalogue);
}
