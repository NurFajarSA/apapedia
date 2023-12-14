package com.apapedia.webapp.dto.request;

import java.io.InputStream;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCatalogueDTO extends CreateCatalogueDTO{
    @JsonIgnore
    private transient InputStream inputStream;
    
    private String imageData;   

    private UUID id;
}
