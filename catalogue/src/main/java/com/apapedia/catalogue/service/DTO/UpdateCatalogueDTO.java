package com.apapedia.catalogue.service.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCatalogueDTO{
    private UUID id;
    private String productName; 
    private int price;
    private String productDescription;
    private UUID idCategory;
    private byte[] image;
}
