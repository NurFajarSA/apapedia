package com.apapedia.order.controller;

import com.apapedia.order.dto.request.BuyNowDTO;
import com.apapedia.order.dto.response.TemplateRes;
import com.apapedia.order.model.Order;
import com.apapedia.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value= "/create/cart/{cartId}")
    public ResponseEntity<?> addOrderByCartId(@PathVariable("cartId") UUID cartId){
        try{
            Map<UUID, Order> order = orderService.addOrderByCartId(cartId);
            return new ResponseEntity<>(new TemplateRes("Order created", order), HttpStatus.CREATED);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/create/buy-now")
    public ResponseEntity<?> addOrderBuyNow(@RequestBody BuyNowDTO buyNowDTO) {
        try {
            Order order = orderService.addOrderBuyNow(buyNowDTO);
            return new ResponseEntity<>(new TemplateRes("Order created", order), HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("orderId") UUID orderId, @RequestParam("status") int status){
        try{
            Order order = orderService.updateOrderStatus(orderId, status);
            return new ResponseEntity<>(new TemplateRes("Order updated", order), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<?> getOrderByCustomerId(@PathVariable("customerId") UUID customerId){
        try{
            List<Order> listOrder = orderService.getOrderByCustomerId(customerId);
            return new ResponseEntity<>(new TemplateRes("Order found", listOrder), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/seller/{sellerId}")
    public ResponseEntity<?> getOrderBySellerId(@PathVariable("sellerId") UUID sellerId){
        try{
            List<Order> listOrder = orderService.getOrderBySellerId(sellerId);
            return new ResponseEntity<>(new TemplateRes("Order found", listOrder), HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping(value = "/top5-status-order")
    public Map<Integer,Long> top5Month(){
        return orderService.getStatusCounts();
    }

    // @GetMapping(value = "orderItem/top5-sold-this-month")
    // public Map<Integer, Long> top5Sold() {
    //     return orderService.getSalesCounts();

    // }

//    @GetMapping("/orderItem/perday")
//    public ResponseEntity<List<Integer>> getSalesPerDayMonth() {
//        List<Integer> orderPerDay = orderService.getSalesPerDayForMonth();
//        return new ResponseEntity<>(orderPerDay, HttpStatus.OK);
//    }
}
