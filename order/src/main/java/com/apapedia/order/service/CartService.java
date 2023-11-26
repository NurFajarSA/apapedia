package com.apapedia.order.service;

import java.util.UUID;

import com.apapedia.order.dto.request.AddCartItemRequestDTO;
import com.apapedia.order.dto.request.UpdateCartItemRequestDTO;
import com.apapedia.order.model.Cart;

public interface CartService {

    Cart addCart(Cart cart);

    Cart addCartItem(AddCartItemRequestDTO cartItemDTO);

    Cart updateCartItem(UpdateCartItemRequestDTO cartItemDTO);

    Cart getCartById(UUID cartId);
    
}
