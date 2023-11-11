package com.apapedia.user.model;

import java.util.UUID;

import jakarta.persistence.*;
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
    
    @Column(name="cart_id", nullable=true)
    private UUID cartId;
}
