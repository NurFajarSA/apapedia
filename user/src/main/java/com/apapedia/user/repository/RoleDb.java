package com.apapedia.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apapedia.user.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDb extends JpaRepository<Role, Long> {
    List<Role> findAll();
    Optional<Role> findByRole(String role);
}
