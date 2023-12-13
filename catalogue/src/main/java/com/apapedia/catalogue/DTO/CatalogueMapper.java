package com.apapedia.catalogue.DTO;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import com.apapedia.catalogue.model.Catalogue;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {
    Catalogue updateCatalogueDTOToCatalogue(UpdateCatalogueDTO updateCatalogueDTO);
    UpdateCatalogueDTO catalogueToUpdateCatalogueDTO(Catalogue catalogue);

    // New method for mapping NewCatalogueDTO to Catalogue
    // @Mapping(target = "id", ignore = true) // Assuming ID is auto-generated
    // Catalogue newCatalogueDTOToCatalogue(NewCatalogueDTO newCatalogueDTO);
}
