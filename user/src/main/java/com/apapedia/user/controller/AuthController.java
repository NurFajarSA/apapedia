package com.apapedia.user.controller;

import com.apapedia.user.constant.Constant;
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
    public ResponseEntity<TemplateRes<LoginRes>> login(@RequestBody LoginReq loginReq) {
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
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        // Salah password
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TemplateRes<>(false, "Invalid Credentials!", null));
        }

        if (user.getRole().getRole().equals(Constant.ROLE_SELLER)) {
            if (userService.login(user) == Constant.ROLE_SELLER){
                return ResponseEntity.ok().body(new TemplateRes<>(true, "SSO Login Success!", null));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new TemplateRes<>(false, "Invalid Credentials!", null));
            }
        } else {
            String tokenCustomer = userService.login(user);
            return ResponseEntity.ok().body(new TemplateRes<>(true, "Login Success!", new LoginRes(tokenCustomer)));
        }

    }

    @PostMapping("/signup/customer")
    private ResponseEntity<TemplateRes<UserModel>> signUpCustomer(@Valid @RequestBody SignUpUserRequestDTO newUser) {
        try {
            UserModel user = userService.signUpCustomer(newUser);
            return ResponseEntity.ok().body(new TemplateRes<>(true, "Success!", user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
    }

    @PostMapping("/signup/seller")
    private ResponseEntity<TemplateRes<UserModel>> signUpSeller(@Valid @RequestBody SignUpUserRequestDTO newUser) {
        try {
            UserModel user = userService.signUpSeller(newUser); 
            return ResponseEntity.ok().body(new TemplateRes<>(true, "Success!", user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
    }
}
