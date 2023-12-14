package com.apapedia.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginReq {
    private String usernameEmail;
    private String password;
}
