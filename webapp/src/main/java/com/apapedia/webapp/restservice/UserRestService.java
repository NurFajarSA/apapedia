package com.apapedia.webapp.restservice;

import java.io.IOException;

import com.apapedia.webapp.dto.request.CreateUserRequestDTO;
import com.apapedia.webapp.dto.response.ReadUserResponseDTO;

public interface UserRestService {
    
    ReadUserResponseDTO registerUser (CreateUserRequestDTO userDTO) throws IOException, InterruptedException;
    
}
