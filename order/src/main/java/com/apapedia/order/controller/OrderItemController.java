package com.apapedia.order.controller;

import com.apapedia.order.model.OrderItem;
import com.apapedia.order.model.Order;
import com.apapedia.order.repository.OrderDb;
import com.apapedia.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderItemController {

    @Autowired
    private OrderService orderService;



    @GetMapping(value = "orderItem/top5-sold-this-month")
    public Map<Integer, Long> top5Sold() {
        return orderService.getSalesCounts();

    }

//    @GetMapping("/orderItem/perday")
//    public ResponseEntity<List<Integer>> getSalesPerDayMonth() {
//        List<Integer> orderPerDay = orderService.getSalesPerDayForMonth();
//        return new ResponseEntity<>(orderPerDay, HttpStatus.OK);
//    }





}