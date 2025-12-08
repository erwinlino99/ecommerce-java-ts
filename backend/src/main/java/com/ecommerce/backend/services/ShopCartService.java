package com.ecommerce.backend.services;

import com.ecommerce.backend.repositories.ShopCartRepository;

public class ShopCartService {
 
    private ShopCartRepository repo;

    public ShopCartService(ShopCartRepository repo){
        this.repo=repo;
    }
    
}
