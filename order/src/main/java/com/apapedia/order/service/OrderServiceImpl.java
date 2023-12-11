package com.apapedia.order.service;


import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;
import com.apapedia.order.repository.OrderDb;
import com.apapedia.order.repository.OrderItemDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apapedia.order.model.Order;
import com.apapedia.order.repository.OrderDb;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDb orderDb;

    @Autowired
    private OrderItemDb orderItemDb;

    @Override
    public List<Order> getOrderByCustomer(UUID customer) {
        return orderDb.findByCustomer(customer);
    }

    @Override
    public List<Order> getOrderBySeller(UUID seller) {
        return orderDb.findBySeller(seller);
    }

    @Override
    public Order addOrder(Order order){
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setStatus(0);
        return orderDb.save(order);
    }

    @Override
    public Order getOrderById(UUID id) {
        for (Order order : orderDb.findAll()) {
            if (order.getOrderId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order updateStatusOrder(Order orderDTO, UUID id){
        Order order = getOrderById(id);
        if(order != null){
            order.setStatus(orderDTO.getStatus());
        }
        return order;
    }

    @Override
    public Map<Integer, Long> getStatusCounts(){
        List<Order> listOrder = orderDb.findAll();
        Map<Integer, Long> statusCounts = new HashMap<>();

        for (Order order : listOrder){
            int status = order.getStatus();
            long count = statusCounts.getOrDefault(status, 0L);
            statusCounts.put(status, count + 1);
        }
        return statusCounts;
    }

    @Override
    public Map<Integer, Long> getSalesCounts() {
            List<OrderItem> listOrderItem = orderItemDb.findAll();
            Map<Integer, Long> salesCounts = new HashMap<>();

            for (OrderItem orderItem : listOrderItem) {
                int productName = orderItem.getProductName();
                int quantity = orderItem.getQuantity();

                salesCounts.put(productName, salesCounts.getOrDefault(productName, 0L) + quantity);
            }
            List<Map.Entry<Integer, Long>> sortedSalesList = salesCounts.entrySet().stream()
                    .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                    .limit(5)
                    .collect(Collectors.toList());

            Map<Integer, Long> top5ProductSales = new LinkedHashMap<>();
            for (Map.Entry<Integer, Long> entry : sortedSalesList) {
                top5ProductSales.put(entry.getKey(), entry.getValue());
            }

            return top5ProductSales;
        }

//        public List<Integer> getSalesPerDayForMonth(UUID userId) {
//            LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1, 0, 0);
//            LocalDateTime endOfMonth = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().lengthOfMonth(), 23, 59);
//
//            List<Order> orders = orderDb.findBySellerAndCreatedAtBetween(userId, startOfMonth, endOfMonth);
//
//            List<Integer> salesPerDay = orders.stream()
//                    .map(order -> order.getListOrderItem().stream()
//                            .mapToInt(orderItem -> orderItem.getQuantity()).sum())
//                    .collect(Collectors.toList());
//
//            return salesPerDay;
//        }
}
