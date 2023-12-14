package com.apapedia.webapp.dto.request;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCatalogueDTO {
    private UUID id;
    private UUID sellerId;
    private int price;
    private String productName;  
    private String productDescription;
    private int stock;
    private MultipartFile image;
    private UUID category;
}
