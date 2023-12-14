package com.apapedia.webapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProfileRequestDTO {

    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;

}
