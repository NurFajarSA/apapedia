package com.apapedia.user.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequestDTO{
    private UUID id;
    private String email;
    private String address;
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }
}
