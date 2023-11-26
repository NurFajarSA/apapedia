package com.apapedia.order.service;

import com.apapedia.order.model.Order;

import java.util.Map;
import java.util.UUID;
import java.util.List;

public interface OrderService {

    List<Order> getOrderByCustomer(UUID customer);
    List<Order> getOrderBySeller(UUID seller);
    Order addOrder (Order order);
    Order getOrderById(UUID id);
    Order updateStatusOrder(Order orderDTO, UUID id);
    Map<Integer,Long > getStatusCounts();
    Map<Integer, Long> getSalesCounts();
}