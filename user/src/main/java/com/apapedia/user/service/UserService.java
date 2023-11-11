package com.apapedia.user.service;

import com.apapedia.user.model.User;

import java.util.UUID;

public interface UserService {
    User getUserbyId(UUID id);
    User signUp(User newUser);
    // jwt
    User updateUser(User updatedUser);
    User deletedUser(User deletedUser);
    // balace
}
