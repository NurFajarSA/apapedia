package com.apapedia.user.repository;

import java.util.Optional;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apapedia.user.model.Seller;

public interface SellerDb extends JpaRepository<Seller, UUID>{

    Optional<Seller> findById(java.util.UUID id);
    
}
