package com.apapedia.order.controller;

import com.apapedia.order.model.CartItem;
import com.apapedia.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.apapedia.order.dto.request.AddCartItemRequestDTO;
import com.apapedia.order.dto.request.UpdateCartItemRequestDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.service.CartService;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/order/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    /**
     * Digunakan untuk user service
     * @param userId
     * @return new Cart
     */
    @PostMapping("/add")
    public Cart addCart(@RequestParam(name = "userId") UUID userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setListCartItem(new ArrayList<CartItem>());
        return cartService.addCart(cart);
    }

    @PostMapping("/cartItem/add")
    public Cart addCartItem(@RequestBody AddCartItemRequestDTO cartItemDTO) {
        return cartService.addCartItem(cartItemDTO);
    }

    @PutMapping("/cartItem/update")
    public Cart updateCartItem(@RequestBody UpdateCartItemRequestDTO cartItemDTO) {
        return cartService.updateCartItem(cartItemDTO);

    @GetMapping("/items/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable UUID userId) {
        return cartService.getCartItemsByUserId(userId);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void deleteCartItem(@PathVariable UUID cartItemId) {
        cartService.deleteCartItem(cartItemId);

    }
}
