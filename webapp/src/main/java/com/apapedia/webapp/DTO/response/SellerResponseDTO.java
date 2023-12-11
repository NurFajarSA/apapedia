package com.apapedia.webapp.DTO.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SellerResponseDTO {
    private String username;
    private String email;
    private String address;
    private String category;
    private long balance;
}
