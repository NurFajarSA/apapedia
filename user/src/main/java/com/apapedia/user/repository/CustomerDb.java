package com.apapedia.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apapedia.user.model.Customer;

public interface CustomerDb extends JpaRepository<Customer, UUID>{

    Optional<Customer> findById(java.util.UUID id);
    
}
