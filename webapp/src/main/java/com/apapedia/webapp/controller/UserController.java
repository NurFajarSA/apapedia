package com.apapedia.webapp.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import com.apapedia.webapp.dto.SellerMapper;
import com.apapedia.webapp.restservice.SellerRestService;
import com.apapedia.webapp.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.apapedia.webapp.dto.request.CreateUserRequestDTO;
import com.apapedia.webapp.dto.request.UpdateProfileRequestDTO;
import com.apapedia.webapp.restservice.UserRestService;
import com.apapedia.webapp.security.xml.ServiceResponse;
import com.apapedia.webapp.setting.Setting;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    UserRestService userRestService;

    @Autowired
    SellerRestService sellerRestService;

    @Autowired
    SellerMapper sellerMapper;

    @Autowired
    JwtUtils jwtUtils;

    private WebClient webClient = WebClient.builder()
                    .codecs(configurer -> configurer.defaultCodecs()
                    .jaxb2Decoder(new Jaxb2XmlDecoder()))
                    .build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(@RequestParam(value = "ticket", required = false) String ticket,
    HttpServletRequest request) {
        ServiceResponse serviceResponse = this.webClient.get().uri(
            String.format(
                Setting.SERVER_VALIDATE_TICKET,
                ticket,
                Setting.CLIENT_LOGIN
            )
        ).retrieve().bodyToMono(ServiceResponse.class).block();
        
        // Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();


        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "dummy", null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        var token = userRestService.getToken(username, "dummy");

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        httpSession.setAttribute("token", token);

        return new ModelAndView("redirect:/catalogue/view-all");
    }

    @GetMapping("/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);    
    }



    @GetMapping("/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute CreateUserRequestDTO createUserDTO)
            throws IOException, InterruptedException {
        createUserDTO.setPassword("dummy");
        createUserDTO.setRole("Seller");
        createUserDTO.setEmail(createUserDTO.getUsername() + "@ui.ac.id");
        userRestService.registerUser(createUserDTO);
        return "redirect:/catalogue/view-all";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        var token = httpSession.getAttribute("token");
        String sellerId = jwtUtils.getClaimsFromJwtToken(token.toString()).substring(8, 44);
        var seller = sellerRestService.readSeller(UUID.fromString(sellerId), token.toString());
        model.addAttribute("seller", seller);
        return "profile";
    }

    @GetMapping("/edit-profile")
    public String editProfile(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        var token = httpSession.getAttribute("token");
        String sellerId = jwtUtils.getClaimsFromJwtToken(token.toString()).substring(8, 44);
        
        // Fetch the existing seller information
        var seller = sellerRestService.readSeller(UUID.fromString(sellerId), token.toString());
        
        // Add the seller information to the model for prefilling the form
        model.addAttribute("seller", seller);
        
        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute UpdateProfileRequestDTO updateProfileDTO, HttpServletRequest request)
            throws IOException, InterruptedException {
        HttpSession httpSession = request.getSession();
        var token = httpSession.getAttribute("token");
        String sellerId = jwtUtils.getClaimsFromJwtToken(token.toString()).substring(8, 44);
    
        // Fetch the existing seller information
        var existingSeller = sellerRestService.readSeller(UUID.fromString(sellerId), token.toString());
    
        // Perform the update
        updateProfileDTO.setId(sellerId);
        updateProfileDTO.setName(existingSeller.getName());
        updateProfileDTO.setUsername(existingSeller.getUsername());
        updateProfileDTO.setPassword("dummy");
        sellerRestService.updateSeller(UUID.fromString(sellerId), updateProfileDTO, token.toString());
    
        return "redirect:/profile";
    }
    

    @GetMapping("/withdraw")
    public String formWithdraw(Model model, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        var token = httpSession.getAttribute("token");
        String sellerId = jwtUtils.getClaimsFromJwtToken(token.toString()).substring(8, 44);
        var seller = sellerRestService.readSeller(UUID.fromString(sellerId), token.toString());

        var sellerDTO= sellerMapper.sellerToUpdateSellerDTO(seller);
        model.addAttribute("seller", sellerDTO);
        return "withdraw";
    }


    @PostMapping("/withdraw")
    public String postWithdraw(@RequestParam("withdraw") long withdraw, Model model, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        var token = httpSession.getAttribute("token");
        String sellerId = jwtUtils.getClaimsFromJwtToken(token.toString()).substring(8, 44);
        var seller = sellerRestService.withdraw(UUID.fromString(sellerId), token.toString(), withdraw);
        model.addAttribute("seller", seller);
        return "redirect:/profile";
    }
}
