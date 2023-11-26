package com.apapedia.order.service;

import com.apapedia.order.model.CartItem;
import com.apapedia.order.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getCartItemsByUserId(UUID userId) {
        return cartItemRepository.findByCart_UserId(userId);
    }

    @Override
    public void deleteCartItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
