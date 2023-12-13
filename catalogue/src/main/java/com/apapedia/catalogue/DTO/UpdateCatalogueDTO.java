package com.apapedia.catalogue.DTO;

import jakarta.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero(message = "Price must be positive or zero")
    private int price;
    private String productDescription;
    private int stock;
    private CategoryDTO category;
    @Data
    public static class CategoryDTO {
        private UUID id;
        private String name;
    }
    private byte[] image;
}
