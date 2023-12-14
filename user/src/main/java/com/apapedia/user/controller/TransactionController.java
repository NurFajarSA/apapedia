package com.apapedia.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.user.service.UserService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private UserService userService;

    @PutMapping("/withdraw")
    private ResponseEntity<?> withdrawCustomer(@RequestParam("amount") long amount, @RequestParam("customerId") UUID customerId) {
        try{
            var user = userService.withdrawCustomer(amount, customerId); 
            return ResponseEntity.ok().body(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/top-up")
    private ResponseEntity<?> topUpSeller(@RequestParam("amount") long amount, @RequestParam("sellerId") UUID sellerId) {
        try{
            var user = userService.topUpSeller(amount, sellerId); 
            return ResponseEntity.ok().body(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
