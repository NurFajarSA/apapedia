package com.apapedia.order.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.order.model.Order;
import com.apapedia.order.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/order/view-all-order-customer/{customer}")
    public List<Order> getOrderByCustomer(@PathVariable("customer") UUID customer) {
        try{
            return orderService.getOrderByCustomer(customer);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id customer " + customer + " not found"
            );
        }
    }

    @GetMapping("/order/view-all-order-seller")
    public List<Order> getOrderBySeller() {
        UUID dummy = UUID.fromString("8832bfad-28f3-49cb-bc09-b9939b6ec873");
        try{
            return orderService.getOrderBySeller(dummy);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id seller " + dummy + " not found"
            );
        }
    }
}
