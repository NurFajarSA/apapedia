package com.apapedia.webapp.DTO;

import com.apapedia.webapp.dto.UpdateCatalogueDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

import com.apapedia.webapp.model.Catalogue;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {
    UpdateCatalogueDTO catalogueToUpdateCatalogueDTO(Catalogue catalogue);
    Catalogue updateCatalogueDTOToCatalogue(UpdateCatalogueDTO catalogueDTO);
    // Catalogue createCatalogueDTOToCatalogue(CreateCatalogueDTO catalogueDTO);
}
