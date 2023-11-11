package com.apapedia.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apapedia.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;
}
