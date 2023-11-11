package com.apapedia.user.model;

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
@DiscriminatorValue("SELLER")
@Entity
public class Seller extends User {
    @NotNull
    @Column(name="category", nullable=false)
    private String category;
}
