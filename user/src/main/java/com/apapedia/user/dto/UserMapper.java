package com.apapedia.user.dto;

import org.mapstruct.Mapper;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User signUpUserRequestDTOToUser(SignUpUserRequestDTO userDTO);
    User updateUserRequestDTOToUser(UpdateUserRequestDTO userDTO);
}
