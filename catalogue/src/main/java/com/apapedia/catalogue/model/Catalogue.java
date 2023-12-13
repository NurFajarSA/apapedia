package com.apapedia.catalogue.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "catalog")
@JsonIgnoreProperties(value = {"image"}, allowSetters = true)
public class Catalogue {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name="id_seller")
    private UUID idSeller;

    @NotNull
    @Column(name="price", nullable=false)
    private int price;

    @NotNull
    @Column(name="product_name", nullable=false)
    private String productName;  

    @NotNull
    @Column(name="product_description", nullable=false)
    private String productDescription;

    // @NotNull
    // @Column(name="id_category", nullable=false)
    // private UUID idCategory;

    @NotNull
    @Column(name="stock", nullable=false)
    private int stock;

    @Lob
    @Column(name = "image", nullable = true)
    private byte[] image;


    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

}
