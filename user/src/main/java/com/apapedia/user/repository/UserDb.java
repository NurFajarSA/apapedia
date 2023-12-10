package com.apapedia.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.user.model.UserModel;

import java.util.UUID;

@Repository
public interface UserDb extends JpaRepository<UserModel, UUID>{

    UserModel findByEmail(String email);

    UserModel findByUsername(String username);
    
}
