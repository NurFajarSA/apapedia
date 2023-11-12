package com.apapedia.user.service;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.User;

import java.util.UUID;

public interface UserService {
    User getUserbyId(UUID id);
    User getUserbyEmail(String email);
    User getUserbyUsername(String username);
    User signUp(SignUpUserRequestDTO newUser);
    // jwt
    User updateUser(UpdateUserRequestDTO updatedUser);
    User deletedUser(User deletedUser);
    // balace
}
