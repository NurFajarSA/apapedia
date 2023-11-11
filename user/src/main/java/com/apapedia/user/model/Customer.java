package com.apapedia.user.model;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CUSTOMER")
@Entity
public class Customer extends User {
    @NotNull
    @Column(name="cart_id", nullable=false)
    private UUID cartId;
}
