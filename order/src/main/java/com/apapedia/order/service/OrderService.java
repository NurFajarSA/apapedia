package com.apapedia.order.service;

import com.apapedia.order.dto.request.BuyNowDTO;
import com.apapedia.order.model.Order;

import java.util.Map;
import java.util.UUID;
import java.util.List;

public interface OrderService {
    Map<UUID, Order> addOrderByCartId(UUID cartId);
    Order addOrderBuyNow(BuyNowDTO buyNowDTO);
    Order updateOrderStatus(UUID orderId, int status);
    List<Order> getOrderByCustomerId(UUID customerId);
    List<Order> getOrderBySellerId(UUID sellerId);

    Map<Integer,Long > getStatusCounts();
    // Map<Integer, Long> getSalesCounts();
//    List<Integer> getSalesPerDayForMonth(UUID userId);
    
}