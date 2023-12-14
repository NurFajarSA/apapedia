package com.apapedia.order.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemRequestDTO{
    private UUID cartItemId;
    @Positive
    private int quantity;
}
