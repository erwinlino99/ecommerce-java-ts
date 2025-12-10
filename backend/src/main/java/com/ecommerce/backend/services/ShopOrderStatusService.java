package com.ecommerce.backend.services;

import com.ecommerce.backend.repositories.ShopOrderStatusRepository;

public class ShopOrderStatusService {

    private ShopOrderStatusRepository repo;

    public ShopOrderStatusService(ShopOrderStatusRepository repo) {
        this.repo = repo;
    }
}
