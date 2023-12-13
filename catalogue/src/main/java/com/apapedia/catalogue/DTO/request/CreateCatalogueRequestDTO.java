package com.apapedia.catalogue.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class CreateCatalogueRequestDTO {

    private UUID sellerId;
    @PositiveOrZero
    private int price;
    @NotBlank(message = "Nama Produk tidak boleh kosong")
    private String productName;
    private String productDescription;
    private int stock;
    private MultipartFile imageFile;
    private UUID categoryId;
}
