package com.apapedia.user.dto;

import org.mapstruct.Mapper;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserModel signUpUserRequestDTOToUser(SignUpUserRequestDTO userDTO);
}
