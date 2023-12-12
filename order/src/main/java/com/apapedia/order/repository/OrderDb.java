package com.apapedia.order.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Order;
import java.util.List;


@Repository
public interface OrderDb extends JpaRepository<Order, UUID>{
    List<Order> findByCustomerId(UUID customerId);
    List<Order> findBySellerId(UUID sellerId);
    List<Order> findBySellerIdAndCreatedAtBetween(UUID sellerId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
