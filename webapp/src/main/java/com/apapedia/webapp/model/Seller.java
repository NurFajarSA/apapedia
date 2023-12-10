package com.apapedia.webapp.model;

import com.apapedia.webapp.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="id_user")
@Entity
public class Seller extends UserModel {

    @Column(name="category", nullable=true)
    private String category;
}

