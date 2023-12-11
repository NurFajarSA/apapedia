package com.apapedia.user.controller;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.user.constant.Constant;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.UserModel;
import com.apapedia.user.security.jwt.JwtUtils;
import com.apapedia.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/{id}")
    private ResponseEntity<?> getUserById(@PathVariable UUID id) {
        try {
            UserModel user = userService.getUserById(id);
            if (user.getRole().getRole().equals(Constant.ROLE_CUSTOMER)) {
                return ResponseEntity.ok(userService.getCustomerById(id));
            } else {
                return ResponseEntity.ok(userService.getSellerById(id));
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PutMapping("/update")
    private ResponseEntity<UserModel> updateUser(@Valid @RequestBody UpdateUserRequestDTO updatedUser,
            @RequestHeader HashMap<String, String> headers) {
        return ResponseEntity.ok(userService.updateUser(updatedUser, jwtUtils.getTokenFromHeader(headers)));
        
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable UUID id, @RequestHeader HashMap<String, String> headers) {
        userService.deleteUser(id, jwtUtils.getTokenFromHeader(headers));
        return ResponseEntity.ok("User deleted");
    }

    @PutMapping("/update-balance/{id}")
    private UserModel updateBalance(@PathVariable UUID id, @RequestParam("amount") long amount, @RequestHeader HashMap<String, String> headers) {
        return userService.updateBalance(id, amount, jwtUtils.getTokenFromHeader(headers));
    }


}
