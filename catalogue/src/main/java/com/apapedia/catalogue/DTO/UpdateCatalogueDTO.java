package com.apapedia.catalogue.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.apapedia.catalogue.model.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCatalogueDTO{
    private UUID id;
    private String productName; 
    private int price;
    private String productDescription;
    private Category category;
    private byte[] image;
}
