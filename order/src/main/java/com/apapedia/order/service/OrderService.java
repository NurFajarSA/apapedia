package com.apapedia.order.service;

import java.util.List;
import java.util.UUID;

import com.apapedia.order.model.Order;

public interface OrderService {
    List<Order> getOrderByCustomer(UUID customer);
    List<Order> getOrderBySeller(UUID seller);
}
