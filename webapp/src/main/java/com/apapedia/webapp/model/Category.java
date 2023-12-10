package com.apapedia.webapp.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    private UUID id;
    private String name;
    private List<Catalogue> listCatalogue;
}
