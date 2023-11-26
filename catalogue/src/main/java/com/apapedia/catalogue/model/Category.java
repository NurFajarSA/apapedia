package com.apapedia.catalogue.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @Column(name= "category_name" ,nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Catalogue> catalogueList;

}
