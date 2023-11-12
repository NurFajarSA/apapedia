package com.apapedia.user.service;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.User;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User getUserbyId(UUID id);
    User getUserbyEmail(String email);
    User signUp(SignUpUserRequestDTO newUser);
    // jwt
    User updateUser(UpdateUserRequestDTO updatedUser);
    User deletedUser(User deletedUser);
    // balace
}
