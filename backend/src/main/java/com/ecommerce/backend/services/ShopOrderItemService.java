package com.ecommerce.backend.services;

import com.ecommerce.backend.repositories.ShopOrderItemRepository;

public class ShopOrderItemService {

    @SuppressWarnings("unused")
    private final ShopOrderItemRepository repo;

    public ShopOrderItemService(ShopOrderItemRepository repo){
        this.repo=repo;
    }

}
