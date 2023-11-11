package com.apapedia.user.model;

import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Entity
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
@Table(name = "user")
public abstract class User {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Column(name="username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name="password", nullable = false)
    private String password;
    
    @NotNull
    @Column(name="email", nullable = false)
    private String email;
    
    @NotNull
    @Column(name="address", nullable = false)
    private String address;
    
    @NotNull
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @NotNull
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @NotNull
    @Column(name="is_deleted", nullable = false)
    private Boolean isDeleted;
}
