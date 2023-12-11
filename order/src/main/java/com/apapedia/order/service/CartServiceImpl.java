package com.apapedia.order.service;

import java.util.List;
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
    public Cart addCartItem(AddCartItemRequestDTO cartItemDTO) {
        Cart cart = getCartById(cartItemDTO.getCartId());
        CartItem cartItem = cartMapper.addCartItemRequestDTOtoCartItem(cartItemDTO);
        cartItem = cartItemDb.save(cartItem);
        cartItem.setCart(cart);
        cartItem.setProductId(UUID.randomUUID()); 

        updateTotalPrice(cart);
        return cartDb.save(cart);
    }

    @Override
    public Cart updateCartItem(UpdateCartItemRequestDTO cartItemDTO) {
        CartItem cartItem = cartItemDb.findById(cartItemDTO.getCartItemId()).get();
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItemDb.save(cartItem);

        Cart cart = cartDb.findById(cartItem.getCart().getCartId()).get();
        updateTotalPrice(cart);
        return cart;
    }

    @Override
    public Cart getCartById(UUID cartId) {
        return cartDb.findById(cartId).get();
    }
    
    private void updateTotalPrice(Cart cart) {
        // TODO: get price product
        // cart.setTotalPrice(cart.getListCartItem().stream().mapToInt(cartItem -> cartItem.getQuantity() * {{get price product}}).sum());
    }

    @Override
    public List<CartItem> getCartItemsByUserId(UUID userId) {

        Cart cart = cartDb.findByCartUserId(userId);
        return cart.getListCartItem();
    }

    @Override
    public void deleteCartItem(UUID cartItemId) {
        cartDb.deleteById(cartItemId);
    }
}
