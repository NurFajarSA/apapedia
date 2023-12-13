package com.apapedia.catalogue.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.apapedia.catalogue.model.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class NewCatalogueDTO {

    @NotBlank(message = "Nama Produk tidak boleh kosong")
    private String productName;
    @PositiveOrZero
    private int price;
    private String productDescription;
    private int stock;
    private byte[] image;
    private UUID category;
}
