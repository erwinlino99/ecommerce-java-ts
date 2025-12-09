package com.ecommerce.backend.services;

import com.ecommerce.backend.repositories.ShopCartItemRepository;

public class ShopCartItemService {

    private final ShopCartItemRepository repo;

    public ShopCartItemService(ShopCartItemRepository repo){
        this.repo=repo;
    }
    
}
