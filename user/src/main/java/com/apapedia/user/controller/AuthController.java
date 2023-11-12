package com.apapedia.user.controller;

import com.apapedia.user.auth.JwtUtil;
import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.model.User;
import com.apapedia.user.model.request.LoginReq;
import com.apapedia.user.model.response.ErrorRes;
import com.apapedia.user.model.response.LoginRes;
import com.apapedia.user.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            User user = userService.getUserbyEmail(email);
            String token = jwtUtil.createToken(user);
            Date expiredAt = jwtUtil.getExpirationDateFromToken(token);

            LoginRes loginRes = new LoginRes(token, user.getUsername(), user.getId(),"ROLE", expiredAt);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/signup")
    private User signUp(@Valid @RequestBody SignUpUserRequestDTO newUser) {
        try{
            return userService.signUp(newUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
    }
}
