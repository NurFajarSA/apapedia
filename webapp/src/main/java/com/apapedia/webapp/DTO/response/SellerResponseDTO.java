package com.apapedia.webapp.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SellerResponseDTO{
    private String address;
    private long balance;
    private String category;
    private String email;
    private String name;
}