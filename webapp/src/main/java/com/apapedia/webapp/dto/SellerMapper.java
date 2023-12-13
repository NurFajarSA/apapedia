package com.apapedia.webapp.DTO;

import com.apapedia.webapp.DTO.request.UpdateSellerDTO;
import com.apapedia.webapp.model.Seller;
import org.mapstruct.Mapper;
import com.apapedia.webapp.DTO.response.SellerResponseDTO;

@Mapper(componentModel = "spring")
public interface SellerMapper{

    SellerResponseDTO sellerResponseDTO(Seller seller);

    UpdateSellerDTO sellerToUpdateSellerDTO(Seller seller);
}