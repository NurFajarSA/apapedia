package com.apapedia.webapp.restservice;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.apapedia.webapp.dto.request.CreateUserRequestDTO;
import com.apapedia.webapp.dto.response.ReadUserResponseDTO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserRestServiceImpl implements UserRestService {
    
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://apap-102.cs.ui.ac.id")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public ReadUserResponseDTO registerUser(CreateUserRequestDTO createUserDTO)
            throws IOException, InterruptedException {
        try {
            var response = this.webClient
                    .post()
                    .uri("/api/auth/signup/seller")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(createUserDTO)
                    .retrieve()
                    .bodyToMono(ReadUserResponseDTO.class);
            var userSubmitted = response.block();
            return userSubmitted;
        } catch (Exception e) {
            ReadUserResponseDTO userResponseDTO = new ReadUserResponseDTO();
            return userResponseDTO;
        }

    }
}
