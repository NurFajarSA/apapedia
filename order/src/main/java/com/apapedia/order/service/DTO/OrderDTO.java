package com.apapedia.order.service.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

public class OrderDTO {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int status;

    private int totalPrice;

    private UUID customer;

    private UUID seller;
}
