package com.apapedia.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginRes {
    private String token;
}
