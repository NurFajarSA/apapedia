package com.apapedia.webapp.DTO.request;

import java.util.UUID;

import com.apapedia.webapp.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateCatalogueDTO {
    private UUID id;
    private UUID idSeller;
    private long price;
    private String productName;
    private String productDescription;
    private long stock;
    private byte[] image;
    private Category category;
    private boolean deleted;
}
