package com.apapedia.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.Order;
import java.util.List;


@Repository
public interface OrderDb extends JpaRepository<Order, UUID>{
    List<Order> findByCustomer(UUID customer);
    List<Order> findBySeller(UUID seller);
}
