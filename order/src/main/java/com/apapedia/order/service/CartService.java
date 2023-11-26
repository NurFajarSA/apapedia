package com.apapedia.order.service;

import com.apapedia.order.model.CartItem;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartItem> getCartItemsByUserId(UUID userId);
    void deleteCartItem(UUID cartItemId);
}
