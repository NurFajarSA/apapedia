package com.apapedia.order.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apapedia.order.model.Order;
import com.apapedia.order.repository.OrderDb;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDb orderDb;

    @Override
    public List<Order> getOrderByCustomer(UUID customer) {
        return orderDb.findByCustomer(customer);
    }

    @Override
    public List<Order> getOrderBySeller(UUID seller) {
        return orderDb.findBySeller(seller);
    }
}
