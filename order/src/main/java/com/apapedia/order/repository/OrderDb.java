package com.apapedia.order.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Order;
import java.util.List;


@Repository
public interface OrderDb extends JpaRepository<Order, UUID>{
    List<Order> findByCustomer(UUID userId);
    List<Order> findBySeller(UUID userId);
    List<Order> findBySellerAndCreatedAtBetween(UUID userId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
