package com.apapedia.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    private UUID orderItemId;
    private UUID productId;
    private int quantity;
    private String productName;
    private int productPrice;

}

