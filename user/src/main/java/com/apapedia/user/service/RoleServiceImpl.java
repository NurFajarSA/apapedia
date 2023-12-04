package com.apapedia.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apapedia.user.model.Role;
import com.apapedia.user.repository.RoleDb;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDb roleDb;

    @Override
    public List<Role> getAllList() {
        return roleDb.findAll();
    }

    @Override
    public Role getRoleByRoleName(String role) {
        Optional<Role> roleOptional = roleDb.findByRole(role);
        if (roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public Role addRole(Role role) {
        return roleDb.save(role);
    }
}
