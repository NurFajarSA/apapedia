package com.apapedia.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User getUserbyId(UUID id) {
        return userDb.findById(id).get();
    }

    @Override
    public User signUp(SignUpUserRequestDTO newUser) {
        User user = userMapper.signUpUserRequestDTOToUser(newUser);
        user.setPassword(encoder.encode(newUser.getPassword()));
        return userDb.save(user);
    }

    @Override
    public User updateUser(UpdateUserRequestDTO updatedUser) {
        User oldUser = userDb.findById(updatedUser.getId()).get();
        oldUser.setName(updatedUser.getName());
        oldUser.setUsername(updatedUser.getUsername());
        oldUser.setPassword(encoder.encode(updatedUser.getPassword()));
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setBalance(updatedUser.getBalance());
        oldUser.setAddress(updatedUser.getAddress());
        oldUser.setUpdatedAt(updatedUser.getUpdatedAt());
        return userDb.save(oldUser);
    }

    @Override
    public User deletedUser(User deletedUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletedUser'");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDb.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }

    @Override
    public User getUserbyEmail(String email) {
        return userDb.findByEmail(email);
    }
    
}
