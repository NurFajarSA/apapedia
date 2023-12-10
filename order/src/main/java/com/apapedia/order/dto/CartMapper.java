package com.apapedia.order.dto;

import org.mapstruct.Mapper;
import com.apapedia.order.dto.request.AddCartItemRequestDTO;
import com.apapedia.order.dto.request.UpdateCartItemRequestDTO;
import com.apapedia.order.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartItem addCartItemRequestDTOtoCartItem(AddCartItemRequestDTO cartItemDTO); 
    CartItem updateCartItemRequestDTOtoCartItem(UpdateCartItemRequestDTO cartItemDTO); 
}
