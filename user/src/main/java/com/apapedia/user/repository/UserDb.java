package com.apapedia.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.user.model.User;

import java.util.UUID;

@Repository
public interface UserDb extends JpaRepository<User, UUID>{

    User findByEmail(String email);

    User findByUsername(String username);
    
}
