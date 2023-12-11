package com.apapedia.webapp.controller;

import com.apapedia.webapp.dto.SellerMapper;
import com.apapedia.webapp.model.Seller;
import com.apapedia.webapp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class UserController {

    @Autowired
    SellerMapper sellerMapper;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Seller seller, Model model){
        var sellerMap = sellerMapper.sellerResponseDTO(seller);
        model.addAttribute("seller", sellerMap);
        return "profile";
    }
}
