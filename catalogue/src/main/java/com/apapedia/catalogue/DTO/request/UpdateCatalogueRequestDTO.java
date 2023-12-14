package com.apapedia.catalogue.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.InputStream;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCatalogueRequestDTO extends CreateCatalogueRequestDTO{
    @JsonIgnore
    private transient InputStream inputStream;
    
    private String imageData;   
    private UUID id;
}
