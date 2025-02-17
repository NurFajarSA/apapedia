package com.apapedia.order.dto.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequestDTO {
    private UUID cartId;
    private UUID productId;
    private UUID sellerId;
    private String productName;
    private int productPrice;
    private int quantity;
}
