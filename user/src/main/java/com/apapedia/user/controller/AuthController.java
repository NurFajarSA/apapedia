package com.apapedia.user.controller;

import com.apapedia.user.auth.JwtUtil;
import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.model.User;
import com.apapedia.user.model.request.LoginReq;
import com.apapedia.user.model.response.LoginRes;
import com.apapedia.user.model.response.TemplateRes;
import com.apapedia.user.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<TemplateRes<LoginRes>> login(@RequestBody LoginReq loginReq) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Username/email kosong
        if ((loginReq.getEmail() == null || loginReq.getEmail().isEmpty())
                && (loginReq.getUsername() == null || loginReq.getUsername().isEmpty())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new TemplateRes<>(false, "Invalid Request!", null));
        }

        User user;
        if (!loginReq.getEmail().isEmpty()) {
            user = userService.getUserbyEmail(loginReq.getEmail());
        } else {
            user = userService.getUserbyUsername(loginReq.getUsername());
        }

        // Salah password
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TemplateRes<>(false, "Invalid Credentials!", null));
        }

        // Berhasil login
        String token = jwtUtil.createToken(user);
        return ResponseEntity.ok().body(new TemplateRes<>(true, "Login Success!", new LoginRes(token)));

    }

    @PostMapping("/signup")
    private User signUp(@Valid @RequestBody SignUpUserRequestDTO newUser) {
        try {
            return userService.signUp(newUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
    }
}
