package com.apapedia.order.service.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateOrderDTO {

    private UUID orderId;
    private int status;
    private int totalPrice;
    private UUID customer;
    private UUID seller;
}
