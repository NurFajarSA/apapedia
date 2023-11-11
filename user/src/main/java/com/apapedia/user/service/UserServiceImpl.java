package com.apapedia.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apapedia.user.dto.UserMapper;
import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.User;
import com.apapedia.user.repository.UserDb;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDb userDb;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserbyId(UUID id) {
        return userDb.findById(id).get();
    }

    @Override
    public User signUp(SignUpUserRequestDTO newUser) {
        User user = userMapper.signUpUserRequestDTOToUser(newUser);
        return userDb.save(user);
    }

    @Override
    public User updateUser(UpdateUserRequestDTO updatedUser) {
        User user = userMapper.updateUserRequestDTOToUser(updatedUser);
        return userDb.save(user);
    }

    @Override
    public User deletedUser(User deletedUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletedUser'");
    }
    
}
