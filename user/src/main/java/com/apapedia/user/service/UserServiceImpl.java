package com.apapedia.user.service;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.user.constant.Constant;
import com.apapedia.user.dto.UserMapper;
import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.dto.request.UpdateUserRequestDTO;
import com.apapedia.user.model.Customer;
import com.apapedia.user.model.Role;
import com.apapedia.user.model.Seller;
import com.apapedia.user.model.UserModel;
import com.apapedia.user.model.response.WebClientRes;
import com.apapedia.user.repository.CustomerDb;
import com.apapedia.user.repository.SellerDb;
import com.apapedia.user.repository.UserDb;
import com.apapedia.user.security.jwt.JwtUtils;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDb userDb;

    @Autowired
    private CustomerDb customerDb;

    @Autowired
    private SellerDb sellerDb;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RoleService roleService;

    private final WebClient webClientOrder;

    public UserServiceImpl(WebClient.Builder webClientBuilder){
        this.webClientOrder = webClientBuilder.baseUrl("http://sonsulung.com:10104/api").build();
    }


    @Override
    public UserModel getUserById(UUID id) {
        return userDb.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userDb.findByEmail(email);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public UserModel signUpCustomer(SignUpUserRequestDTO newUser) {
        Customer user = userMapper.signUpUserRequestDTOToCustomer(newUser);
        String hashedPassword = encrypt(newUser.getPassword());
        user.setPassword(hashedPassword);
        Role newRole = roleService.getRoleByRoleName(Constant.ROLE_CUSTOMER);
        user.setRole(newRole);

        var cartId = createCartId(user.getId());
        user.setCartId(cartId);

        return customerDb.save(user);
    }

    private UUID createCartId(UUID id) {
        var response = webClientOrder.post().uri("/order/cart/add?userId=" + id.toString()).retrieve().bodyToMono(WebClientRes.class);
        WebClientRes webClientRes = response.block();
        UUID cartId = UUID.fromString(((LinkedHashMap<?, ?>) webClientRes.getData()).get("cartId").toString());
        return cartId;
    }


    @Override
    public UserModel signUpSeller(SignUpUserRequestDTO newUser) {
        Seller user = userMapper.signUpUserRequestDTOToSeller(newUser);
        String hashedPassword = encrypt(newUser.getPassword());
        user.setPassword(hashedPassword);
        Role newRole = roleService.getRoleByRoleName(Constant.ROLE_SELLER);
        user.setRole(newRole);
        user.setCategory(newUser.getCategory());
        
        return sellerDb.save(user);
    }

    @Override
    public String login(UserModel user) {
        return jwtUtils.generateJwtToken(user);
    }

    @Override
    public UserModel updateUser(UpdateUserRequestDTO updatedUser, String token) {
        if (!isSameUser(updatedUser.getId(), token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to update this user");
        }

        UserModel oldUser = userDb.findById(updatedUser.getId()).get();
        if (!updatedUser.getPassword().equals("")) oldUser.setPassword(encrypt(updatedUser.getPassword()));
        if ((getUserByUsername(updatedUser.getUsername()) != null) && (!updatedUser.getUsername().equals(oldUser.getUsername()))) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        if ((getUserByEmail(updatedUser.getEmail()) != null) && (!updatedUser.getEmail().equals(oldUser.getEmail()))) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        
        oldUser.setUsername(updatedUser.getUsername());
        oldUser.setName(updatedUser.getName());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setAddress(updatedUser.getAddress());
        oldUser.setUpdatedAt(updatedUser.getUpdatedAt());
        return userDb.save(oldUser);
    }

    @Override
    public UserModel topUp(UUID id, long amount, String token) {
        UserModel user = userDb.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!isSameUser(id, token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to update this user");
        }
    
        // Add validation for non-negative balance
        if (amount < 0 ) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    
        // Update balance
        user.setBalance(user.getBalance() + amount);
        return userDb.save(user);
    }

    @Override
    public UserModel withdraw(UUID id, long amount, String token) {
        UserModel user = userDb.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!isSameUser(id, token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to update this user");
        }
    
        // Add validation for non-negative balance
        if (amount < 0 || user.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
    
        // Update balance
        user.setBalance(user.getBalance() - amount);
        return userDb.save(user);
    }

    @Override
    public void deleteUser(UUID id, String token) {
        if (!isUserExist(id)) {
            throw new NoSuchElementException("User not found");
        }
        else if (!isSameUser(id, token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to delete this user");
        }
        userDb.deleteById(id);
    }

    @Override
    public boolean isUserExist(UUID id) {
        return userDb.findById(id).isPresent();
    }

    @Override
    public boolean isSameUser(UUID userId, String token) {
        var id = jwtUtils.getClaimsFromJwtToken(token).getSubject();
        return userId.toString().equals(id);
    }

    @Override
    public String getToken(String token) {
        return jwtUtils.getClaimsFromJwtToken(token).getSubject();
    }


    @Override
    public Customer getCustomerById(UUID id) {
        return customerDb.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    @Override
    public Seller getSellerById(UUID id) {
        return sellerDb.findById(id).orElseThrow(() -> new NoSuchElementException("Seller not found"));
    }

    @Override 
    public Seller topUpSeller(long amount, UUID sellerId) {
        var seller = getSellerById(sellerId);
        seller.setBalance(seller.getBalance() + amount);
        return sellerDb.save(seller);
    }

    @Override
    public Customer withdrawCustomer(long amount, UUID customerId) {
        var customer = getCustomerById(customerId);
        if (customer.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }
        customer.setBalance(customer.getBalance() - amount);
        return customerDb.save(customer);
    }
    
}
