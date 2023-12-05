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
        if (loginReq.getUsernameEmail().contains("@")) {
            user = userService.getUserByEmail(loginReq.getUsernameEmail());
        } else {
            user = userService.getUserByUsername(loginReq.getUsernameEmail());
        }

        // Salah password
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TemplateRes<>(false, "Invalid Credentials!", null));
        }

        if (user.getRole().getRole().equals(Constant.ROLE_SELLER)) {
            // TODO: SSO
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TemplateRes<>(false, "Seller cannot login!", null));
        } else{
            String tokenCustomer = userService.login(user);
            return ResponseEntity.ok().body(new TemplateRes<>(true, "Login Success!", new LoginRes(tokenCustomer)));
        }


    }

    @PostMapping("/signup")
    private UserModel signUp(@Valid @RequestBody SignUpUserRequestDTO newUser) {
        try {
            return userService.signUp(newUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
    }
}
