package com.apapedia.webapp.dto.request;

import java.math.BigInteger;

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
    private com.apapedia.webapp.dto.request.CategoryDTO category;
}
