package com.apapedia.webapp.restservice;

import com.apapedia.webapp.model.Seller;

import java.util.UUID;

public interface SellerRestService {
    //    Seller getSeller(Seller seller);
    Seller readSeller(UUID sellerId, String token);
    Seller withdraw(UUID sellerId, String token,long withdraw);
}
