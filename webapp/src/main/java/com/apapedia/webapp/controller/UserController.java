package com.apapedia.webapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.apapedia.webapp.dto.request.CreateUserRequestDTO;
import com.apapedia.webapp.restservice.UserRestService;


@Controller
public class UserController {

    @Autowired
    UserRestService userRestService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute CreateUserRequestDTO createUserDTO)
            throws IOException, InterruptedException {
        createUserDTO.setPassword("hatta");
        createUserDTO.setRole("Seller");
        createUserDTO.setEmail(createUserDTO.getUsername() + "@ui.ac.id");
        userRestService.registerUser(createUserDTO);

        return "redirect:/home";
    }
    
}
