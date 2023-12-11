package com.apapedia.webapp.model;

import com.apapedia.webapp.dto.request.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue {
    private UUID id;
    private BigInteger price;
    private String productName;
    private String productDescription;
    private int stock;
    private byte[] image; // Tambahkan atribut baru untuk representasi Base64 dari gambar
    private CategoryDTO category;
}
