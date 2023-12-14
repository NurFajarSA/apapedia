package com.apapedia.order.controller;

import com.apapedia.order.model.CartItem;
import com.apapedia.order.service.CartService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.apapedia.order.dto.response.TemplateRes;
import com.apapedia.order.model.Cart;

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
    public ResponseEntity<?> addCart(@RequestParam(name = "userId") UUID customerId) {
        // user id pastiin dulu pas di user service
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setListCartItem(new ArrayList<CartItem>());
        cart = cartService.addCart(cart);
        return ResponseEntity.ok(new TemplateRes("Cart created", cart));
    }

    @PostMapping("/cartItem/add")
    public ResponseEntity<?> addCartItem(@RequestBody AddCartItemRequestDTO cartItemDTO) {
        return ResponseEntity.ok(new TemplateRes("Cart item added", cartService.addCartItem(cartItemDTO)));
    }

    @PutMapping("/cartItem/update-quantity")
    public ResponseEntity<?> updateCartItem(@Valid @RequestBody UpdateCartItemRequestDTO cartItemDTO) {
        return ResponseEntity.ok(new TemplateRes("Cart item updated", cartService.updateCartItem(cartItemDTO)));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCartItemsByUserId(@PathVariable UUID customerId) {
        return ResponseEntity.ok(new TemplateRes("Cart items retrieved", cartService.getCartItemsByCustomerId(customerId)));
    }

    @DeleteMapping("/cartItem/{cartItemId}/delete")
    public ResponseEntity<?> deleteCartItem(@PathVariable UUID cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.ok(new TemplateRes("Cart item deleted", null));
    }
}