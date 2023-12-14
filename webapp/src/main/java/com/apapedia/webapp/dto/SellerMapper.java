package com.apapedia.webapp.dto;

import org.mapstruct.Mapper;

import com.apapedia.webapp.dto.request.UpdateSellerDTO;
import com.apapedia.webapp.model.Seller;
import com.apapedia.webapp.dto.response.SellerResponseDTO;

@Mapper(componentModel = "spring")
public interface SellerMapper{

    SellerResponseDTO sellerResponseDTO(Seller seller);

    UpdateSellerDTO sellerToUpdateSellerDTO(Seller seller);
}