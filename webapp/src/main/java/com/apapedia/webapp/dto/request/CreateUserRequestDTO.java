package com.apapedia.webapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequestDTO {
    
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    long balance;
    private String role;
    private String category;

}
