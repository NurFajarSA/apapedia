package com.apapedia.user.service;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.UserModel;

import java.util.UUID;

public interface UserService {
    UserModel getUserById(UUID id);
    UserModel getUserByEmail(String email);
    UserModel getUserByUsername(String username);
    
    UserModel signUp(SignUpUserRequestDTO newUser);
    String login(UserModel user);

    UserModel updateUser(UpdateUserRequestDTO updatedUser, String token);
    UserModel updateBalance(UUID id, long amount, String token);
    void deleteUser(UUID id, String token);

    String encrypt(String password);
    boolean isSameUser(UUID userId, String token);
    boolean isUserExist(UUID userId);
    String getToken(String token);
}
