package com.apapedia.order.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"order"}, allowSetters = true)
@Table(name = "order_item")

public class OrderItem {
    @Id
    private UUID orderItemId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order; 

    @NotNull
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull
    @Column(name = "product_name", nullable = false)
    private int productName;

    @NotNull
    @Column(name = "product_price", nullable = false)
    private int productPrice;
}
