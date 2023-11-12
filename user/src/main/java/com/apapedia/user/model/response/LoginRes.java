package com.apapedia.user.model.response;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginRes {
    private String token;
    private String username;
    private UUID id;
    private String role;
    private Date expiredAt;
    // private Claims claims;
}
