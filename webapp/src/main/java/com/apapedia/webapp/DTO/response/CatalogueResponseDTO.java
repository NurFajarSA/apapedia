package com.apapedia.webapp.dto.response;

import java.util.UUID;

import com.apapedia.webapp.dto.request.CategoryDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogueResponseDTO {
    private UUID id;
    private String productName;
    private String productDescription;
    private CategoryDTO category;
    private byte[] image;
    private int price;
    private int stock;
}

