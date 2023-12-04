package com.apapedia.user.service;

import java.util.List;
import com.apapedia.user.model.Role;

public interface RoleService {
    List<Role> getAllList();
    Role getRoleByRoleName(String role);
    Role addRole(Role role);
}
