package com.apapedia.webapp.DTO.request;

import java.util.UUID;

import com.apapedia.webapp.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateCatalogueDTO extends CreateCatalogueDTO{
    private UUID id;
}
