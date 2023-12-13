package com.apapedia.webapp.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadUserResponseDTO {

    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    private long balance;
    private String role;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;
    private String category;
}
