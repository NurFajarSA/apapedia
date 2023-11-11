package com.apapedia.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.User;
import com.apapedia.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    private User getUserById(@PathVariable UUID id) {
        return userService.getUserbyId(id);
    }

    @PostMapping("/signup")
    private User signUp(@RequestBody SignUpUserRequestDTO newUser) {
        return userService.signUp(newUser);
    }

    @PostMapping("/update")
    private User updateUser(@RequestBody UpdateUserRequestDTO updatedUser) {
        return userService.updateUser(updatedUser);
    }
}
