package com.apapedia.order.controller;

import com.apapedia.order.model.OrderItem;
import com.apapedia.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderItemController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "orderItem/top5-sold-this-month")
    public Map<Integer, Long> top5Sold() {
        return orderService.getSalesCounts();

    }
}