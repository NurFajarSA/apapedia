package com.apapedia.order.service;

import java.util.List;
import java.util.UUID;

import com.apapedia.order.dto.request.AddCartItemRequestDTO;
import com.apapedia.order.dto.request.UpdateCartItemRequestDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;

public interface CartService {
    
    Cart addCart(Cart cart);
    Cart addCartItem(AddCartItemRequestDTO cartItemDTO);
    Cart updateCartItem(UpdateCartItemRequestDTO cartItemDTO);
    Cart getCartById(UUID cartId);
    List<CartItem> getCartItemsByUserId(UUID userId);
    void deleteCartItem(UUID cartItemId);
}
