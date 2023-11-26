package com.apapedia.order.controller;

import com.apapedia.order.model.CartItem;
import com.apapedia.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/items/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable UUID userId) {
        return cartService.getCartItemsByUserId(userId);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void deleteCartItem(@PathVariable UUID cartItemId) {
        cartService.deleteCartItem(cartItemId);
    }
}
