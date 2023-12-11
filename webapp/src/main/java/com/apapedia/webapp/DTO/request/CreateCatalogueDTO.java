package com.apapedia.webapp.DTO.request;

import java.math.BigInteger;
import java.util.UUID;

import com.apapedia.webapp.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCatalogueDTO {
    private BigInteger price;
    private String productName;
    private String productDescription;
    private int stock;
    private byte[] image; // Tambahkan atribut baru untuk representasi Base64 dari gambar
    private CategoryDTO category;
}
