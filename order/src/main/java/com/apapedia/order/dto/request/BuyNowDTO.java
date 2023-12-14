package com.apapedia.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyNowDTO {
    private UUID productId;
    private UUID customerId;
    private UUID sellerId;
    private int productPrice;
    private String productName;
}
