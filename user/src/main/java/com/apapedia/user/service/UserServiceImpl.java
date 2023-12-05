package com.apapedia.user.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.user.constant.Constant;
import com.apapedia.user.dto.UserMapper;
import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.UserModel;
import com.apapedia.user.repository.UserDb;
import com.apapedia.user.security.jwt.JwtUtils;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDb userDb;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserModel getUserById(UUID id) {
        return userDb.findById(id).get();
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userDb.findByEmail(email);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public UserModel signUp(SignUpUserRequestDTO newUser) {
        UserModel user = userMapper.signUpUserRequestDTOToUser(newUser);
        String hashedPassword = encrypt(newUser.getPassword());
        user.setPassword(hashedPassword);
        return userDb.save(user);
    }

    @Override
    public String login(UserModel user) {
        if (user.getRole().getRole() == Constant.ROLE_SELLER) {
            if (!isUserExist(user.getId())) {
                throw new NoSuchElementException("Seller not registered");
            } 
        }
        return jwtUtils.generateJwtToken(user);
    }

    @Override
    public UserModel updateUser(UpdateUserRequestDTO updatedUser, String token) {
        if (isSameUser(updatedUser.getId(), token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to update this user");
        }

        UserModel oldUser = userDb.findById(updatedUser.getId()).get();
        oldUser.setName(updatedUser.getName());
        oldUser.setUsername(updatedUser.getUsername());
        oldUser.setPassword(encrypt(updatedUser.getPassword()));
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setBalance(updatedUser.getBalance());
        oldUser.setAddress(updatedUser.getAddress());
        oldUser.setUpdatedAt(updatedUser.getUpdatedAt());
        return userDb.save(oldUser);
    }

    @Override
    public UserModel updateBalance(UUID id, long amount, String token) {
        UserModel user = userDb.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!isSameUser(id, token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to update this user");
        }
    
        // Add validation for non-negative balance
        if (amount < 0 && user.getBalance() < Math.abs(amount)) {
            throw new IllegalArgumentException("Insufficient balance");
        }
    
        // Update balance
        user.setBalance(user.getBalance() + amount);
        return userDb.save(user);
    }

    @Override
    public void deleteUser(UUID id, String token) {
        if (!isUserExist(id)) {
            throw new NoSuchElementException("User not found");
        }
        else if (!isSameUser(id, token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to delete this user");
        }
        userDb.deleteById(id);
    }

    @Override
    public boolean isUserExist(UUID id) {
        return userDb.findById(id).isPresent();
    }

    @Override
    public boolean isSameUser(UUID userId, String token) {
        return userId.equals(UUID.fromString(jwtUtils.getClaimsFromJwtToken(token).getSubject()));
    }

    @Override
    public String getToken(String token) {
        return jwtUtils.getClaimsFromJwtToken(token).getSubject();
    }
    
}
