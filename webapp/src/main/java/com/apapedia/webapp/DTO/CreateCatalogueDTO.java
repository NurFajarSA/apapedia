package com.apapedia.webapp.dto;

import java.util.UUID;

import com.apapedia.webapp.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCatalogueDTO {
    private UUID id;
    private UUID idSeller;
    private long price;
    private String productName;
    private String productDescription;
    private long stock;
    private String image;
    private String category;
    private boolean deleted;
}
