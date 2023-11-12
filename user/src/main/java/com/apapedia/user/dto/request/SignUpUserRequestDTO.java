package com.apapedia.user.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class SignUpUserRequestDTO {
    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
    
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    @NotEmpty
    private String address;

    private String category = null;

    private UUID cartId = null;
}
