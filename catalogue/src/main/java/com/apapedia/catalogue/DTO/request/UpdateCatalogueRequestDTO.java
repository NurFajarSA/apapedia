package com.apapedia.catalogue.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCatalogueRequestDTO extends CreateCatalogueRequestDTO{
    private UUID id;
}
