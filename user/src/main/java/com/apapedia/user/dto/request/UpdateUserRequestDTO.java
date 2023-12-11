package com.apapedia.user.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserRequestDTO extends SignUpUserRequestDTO{
    private UUID id;
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }
}
