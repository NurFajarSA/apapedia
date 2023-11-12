package com.apapedia.user.controller;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.user.auth.JwtUtil;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.User;
import com.apapedia.user.service.UserService;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private JwtUtil jwtUtil;

    @GetMapping("/{id}")
    private User getUserById(@PathVariable UUID id) {
        try {
            return userService.getUserbyId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PutMapping("/update")
    private User updateUser(@Valid @RequestBody UpdateUserRequestDTO updatedUser,
            @RequestHeader HashMap<String, String> headers) {

        String token = jwtUtil.resolveToken(headers);

        // Jika token tidak ada
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        Claims claims = jwtUtil.parseJwtClaims(token);
        UUID userId = UUID.fromString(claims.getSubject());

        // Jika user yang mengakses bukan user yang ingin diupdate
        if (!userId.equals(updatedUser.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        User user = userService.updateUser(updatedUser);
        return user;
    }
}
