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
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }
            if (user.getRole().getRole().equals(Constant.ROLE_CUSTOMER)) {
                return ResponseEntity.ok(userService.getCustomerById(id));
            } else {
                return ResponseEntity.ok(userService.getSellerById(id));
            }
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequestDTO updatedUser,
            @RequestHeader HashMap<String, String> headers) {
        try {
            var user = userService.updateUser(updatedUser, jwtUtils.getTokenFromHeader(headers));
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }
            return ResponseEntity.ok(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable UUID id, @RequestHeader HashMap<String, String> headers) {
        try{
            userService.deleteUser(id, jwtUtils.getTokenFromHeader(headers));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("User deleted");
    }

    @PutMapping("/update-balance/{id}")
    private ResponseEntity<?> updateBalance(@PathVariable UUID id, @RequestParam("amount") long amount,
            @RequestHeader HashMap<String, String> headers) {
        try{
            var user = userService.updateBalance(id, amount, jwtUtils.getTokenFromHeader(headers)); 
            return ResponseEntity.ok().body(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
