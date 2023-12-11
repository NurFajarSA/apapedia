package com.apapedia.user.controller;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.model.UserModel;
import com.apapedia.user.model.request.LoginReq;
import com.apapedia.user.model.response.LoginRes;
import com.apapedia.user.model.response.TemplateRes;
import com.apapedia.user.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Username/email kosong
        if (loginReq.getUsernameEmail() == null || loginReq.getUsernameEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new TemplateRes<>(false, "Invalid Request!", null));
        }

        UserModel user;
        try {
            if (loginReq.getUsernameEmail().contains("@")) {
                user = userService.getUserByEmail(loginReq.getUsernameEmail());
            } else {
                user = userService.getUserByUsername(loginReq.getUsernameEmail());
            }
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new TemplateRes<>(false, "User not found!", null));
        }

        // Salah password
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TemplateRes<>(false, "Invalid Credentials!", null));
        }

        String tokenCustomer = userService.login(user);
        return ResponseEntity.ok().body(new TemplateRes<>(true, "Login Success!", new LoginRes(tokenCustomer)));
    }

    @PostMapping("/signup/customer")
    private ResponseEntity<?> signUpCustomer(@Valid @RequestBody SignUpUserRequestDTO newUser) {
        try {
            var tempUser = userService.getUserByUsername(newUser.getUsername());
            if (tempUser != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new TemplateRes<>(false, "Username already exists", null));
            }
            UserModel user = userService.signUpCustomer(newUser);
            return ResponseEntity.ok().body(new TemplateRes<>(true, "Success!", user));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signup/seller")
    private ResponseEntity<?> signUpSeller(@Valid @RequestBody SignUpUserRequestDTO newUser) {
        try {
            var tempUser = userService.getUserByUsername(newUser.getUsername());
            if (tempUser != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new TemplateRes<>(false, "Username already exists", null));
            }

            UserModel user = userService.signUpSeller(newUser); 
            return ResponseEntity.ok().body(new TemplateRes<>(true, "Success!", user));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
