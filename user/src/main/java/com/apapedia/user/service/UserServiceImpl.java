package com.apapedia.user.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.apapedia.user.model.User;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public User getUserbyId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserbyId'");
    }

    @Override
    public User signUp(User newUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signUp'");
    }

    @Override
    public User updateUser(User updatedUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public User deletedUser(User deletedUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletedUser'");
    }
    
}
