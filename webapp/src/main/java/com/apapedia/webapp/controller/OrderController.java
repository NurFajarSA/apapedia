package com.apapedia.webapp.controller;

import com.apapedia.webapp.restservice.OrderResrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    OrderResrService orderResrService;

    @GetMapping("/top5-sales-this-month")
    public String topSales(Model model){
        var top5 = orderResrService.chart();
        model.addAttribute("top5", top5);
        return "chart";
    }
}