package com.apapedia.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apapedia.order.dto.CartMapper;
import com.apapedia.order.dto.request.AddCartItemRequestDTO;
import com.apapedia.order.dto.request.UpdateCartItemRequestDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.repository.CartDb;
import com.apapedia.order.repository.CartItemDb;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartDb cartDb;

    @Autowired
    private CartItemDb cartItemDb;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public Cart addCart(Cart cart) {
        return cartDb.save(cart);
    }

    @Override
    public Cart addCartItem(UUID cartId, CartItem cartItem) {
        Cart cart = cartDb.findById(cartId).get();
        cart.getListCartItem().add(cartItem);

        cartItemDb.save(cartItem);
        return cartDb.save(cart);
    }

    @Override
    public Cart addCartItem(AddCartItemRequestDTO cartItemDTO) {
        Cart cart = cartDb.findById(cartItemDTO.getCartId()).get();
        CartItem cartItem = cartMapper.addCartItemRequestDTOtoCartItem(cartItemDTO);
        cart.getListCartItem().add(cartItem);

        cartItemDb.save(cartItem);
        return cartDb.save(cart);
    }

    @Override
    public Cart updateCartItem(UpdateCartItemRequestDTO cartItemDTO) {
        Cart cart = cartDb.findById(cartItemDTO.getCartId()).get();
        CartItem cartItem = cartMapper.updateCartItemRequestDTOtoCartItem(cartItemDTO);

        cartItemDb.save(cartItem);
        return cartDb.save(cart);
    }  

}
