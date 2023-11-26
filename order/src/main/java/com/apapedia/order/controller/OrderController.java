package com.apapedia.order.controller;


import com.apapedia.order.model.Order;
import com.apapedia.order.service.DTO.OrderDTO;
import com.apapedia.order.service.DTO.OrderMapper;
import com.apapedia.order.service.DTO.UpdateOrderDTO;
import com.apapedia.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDTO orderDTO;


    @PostMapping(value= "order/create")
    public Order addOrder(@RequestBody OrderDTO orderDTO){
        var order = orderMapper.orderDTOToOrder(orderDTO);
        return orderService.addOrder(order);
    }

    @PutMapping(value = "/order/update-order")
    public Order updateOrder(UpdateOrderDTO updateOrderDTO){
        var order = orderMapper.updateOrderDTOToOrder(updateOrderDTO);
        return orderService.updateStatusOrder(order, updateOrderDTO.getOrderId() );
    }

    @GetMapping(value = "order/top5-status-order")
    public Map<Integer,Long> top5Month(){
        return orderService.getStatusCounts();
    }




}
