package com.ecommerce.backend.services;

import com.ecommerce.backend.repositories.ShopOrderRepository;

public class ShopOrderService {

    private ShopOrderRepository repo;

    public ShopOrderService(ShopOrderRepository repo) {
        this.repo = repo;
    }
}
