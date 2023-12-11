package com.apapedia.webapp.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDTO {
    private String message;
    private TokenContent content;
    private boolean success;

    @Data
    public static class TokenContent {
        private String token;
    }
}
