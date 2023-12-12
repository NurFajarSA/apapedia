package com.apapedia.order.service;


import com.apapedia.order.dto.request.BuyNowDTO;
import com.apapedia.order.model.Cart;
import com.apapedia.order.model.CartItem;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItem;
import com.apapedia.order.repository.CartDb;
import com.apapedia.order.repository.CartItemDb;
import com.apapedia.order.repository.OrderDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDb orderDb;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDb cartDb;
    
    @Autowired
    private CartItemDb cartItemDb;

    @Override
    public Map<UUID, Order> addOrderByCartId(UUID cartId) {
        Cart cart = cartService.getCartById(cartId);

        Map<UUID, Order> sellerOrder = createOrderBySeller(cart);

        int globalTotalPrice = 0;
        List<CartItem> listCartItem = cart.getListCartItem();
        for (CartItem cartItem : listCartItem) {
            int totalPrice = 0;
            OrderItem orderItem = new OrderItem();
            Order order = sellerOrder.get(cartItem.getSellerId());
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setProductPrice(cartItem.getProductPrice());

            order.getListOrderItem().add(orderItem);

            totalPrice += cartItem.getProductPrice() * cartItem.getQuantity();
            order.setTotalPrice(order.getTotalPrice() + totalPrice);
            orderDb.save(order);
            topUpBalance(order.getSellerId(), totalPrice);
        }
        withdrawBalance(cart.getCustomerId(), globalTotalPrice);
        deleteAllCartItem(cartId);
        return sellerOrder;
    }

    private void withdrawBalance(UUID customerId, int globalTotalPrice) {
        // TODO : withdraw balance
    }

    private void topUpBalance(UUID sellerId, int totalPrice) {
        // TODO : top up balance
    }

    private void deleteAllCartItem(UUID cartId) {
        Cart cart = cartService.getCartById(cartId);
        List<CartItem> listCartItem = cart.getListCartItem();
        for (CartItem cartItem : listCartItem) {
            cartItemDb.delete(cartItem);
        }
        cartDb.save(cart);
    }

    private Map<UUID, Order> createOrderBySeller(Cart cart) {
        Map<UUID, Order> sellerOrder = new HashMap<>();
        List<CartItem> listCartItem = cart.getListCartItem();
        for (CartItem cartItem : listCartItem) {
            UUID sellerId = cartItem.getSellerId();
            Order order = sellerOrder.get(sellerId);
            if (order == null) {
                order = new Order();
                order.setStatus(0);
                order.setCustomerId(cart.getCustomerId());
                order.setSellerId(sellerId);
                order.setListOrderItem(new ArrayList<>());
                sellerOrder.put(sellerId, order);
            }
            orderDb.save(order);
        }
        return sellerOrder;
    }

    @Override
    public Order addOrderBuyNow(BuyNowDTO buyNowDTO) {
        Order order = new Order();
        order.setTotalPrice(buyNowDTO.getProductPrice());
        order.setCustomerId(buyNowDTO.getCustomerId());
        order.setSellerId(buyNowDTO.getSellerId());
        order.setListOrderItem(new ArrayList<>());
        order = orderDb.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProductId(buyNowDTO.getProductId());
        orderItem.setQuantity(1);
        orderItem.setProductName(buyNowDTO.getProductName());
        orderItem.setProductPrice(buyNowDTO.getProductPrice());
        
        order.getListOrderItem().add(orderItem);
        return orderDb.save(order);
        
    }

    @Override
    public Order updateOrderStatus(UUID orderId, int status) {
        Order order = getOrderById(orderId);
        order.setUpdatedAt(LocalDateTime.now());
        order.setStatus(status);
        return orderDb.save(order);
    }

    private Order getOrderById(UUID orderId) {
        return orderDb.findById(orderId).orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @Override
    public List<Order> getOrderByCustomerId(UUID customerId) {
        return orderDb.findByCustomerId(customerId);
    }

    @Override
    public List<Order> getOrderBySellerId(UUID sellerId) {
        return orderDb.findBySellerId(sellerId);
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

    // @Override
    // public Map<Integer, Long> getSalesCounts() {
    //         List<OrderItem> listOrderItem = orderItemDb.findAll();
    //         Map<Integer, Long> salesCounts = new HashMap<>();

    //         for (OrderItem orderItem : listOrderItem) {
    //             String productName = orderItem.getProductName();
    //             int quantity = orderItem.getQuantity();

    //             salesCounts.put(productName, salesCounts.getOrDefault(productName, 0L) + quantity);
    //         }
    //         List<Map.Entry<Integer, Long>> sortedSalesList = salesCounts.entrySet().stream()
    //                 .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
    //                 .limit(5)
    //                 .collect(Collectors.toList());

    //         Map<Integer, Long> top5ProductSales = new LinkedHashMap<>();
    //         for (Map.Entry<Integer, Long> entry : sortedSalesList) {
    //             top5ProductSales.put(entry.getKey(), entry.getValue());
    //         }

    //         return top5ProductSales;
    //     }

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
