package com.apapedia.order.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;

@Repository
public interface CartDb extends JpaRepository<Cart, UUID>{
    List<CartItem> findByCartUserId(UUID userId);
}
