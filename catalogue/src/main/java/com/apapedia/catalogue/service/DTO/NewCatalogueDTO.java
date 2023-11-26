package com.apapedia.catalogue.service.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NewCatalogueDTO {
    private String productName;
    private int price;
    private String productDescription;
    private int stock;
    private String image;
    private UUID idCategory;
}
