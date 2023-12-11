package com.apapedia.user.dto;

import org.mapstruct.Mapper;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.model.Customer;
import com.apapedia.user.model.Seller;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Seller signUpUserRequestDTOToSeller(SignUpUserRequestDTO userDTO);
    Customer signUpUserRequestDTOToCustomer(SignUpUserRequestDTO userDTO);
}
