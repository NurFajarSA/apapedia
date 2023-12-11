package com.apapedia.webapp.restservice;

import java.io.IOException;

import com.apapedia.webapp.DTO.request.CreateUserRequestDTO;
import com.apapedia.webapp.DTO.response.ReadUserResponseDTO;

public interface UserRestService {
    
    ReadUserResponseDTO registerUser (CreateUserRequestDTO userDTO) throws IOException, InterruptedException;
    String getToken(String username, String password);
}
