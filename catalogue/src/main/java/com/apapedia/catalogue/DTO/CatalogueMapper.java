package com.apapedia.catalogue.DTO;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.apapedia.catalogue.model.Catalogue;
import com.apapedia.catalogue.service.CategoryService;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {

    Catalogue updateCatalogueDTOToCatalogue(UpdateCatalogueDTO updateCatalogueDTO);
    UpdateCatalogueDTO catalogueToUpdateCatalogueDTO(Catalogue catalogue);
}
