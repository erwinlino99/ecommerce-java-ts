package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.ShopOrder;
import com.ecommerce.backend.repositories.ShopOrderRepository;

@Service
public class ShopOrderService {

    private ShopOrderRepository repo;

    public ShopOrderService(ShopOrderRepository repo) {
        this.repo = repo;
    }

    public List<ShopOrder> getAllShopOrders(){
        return this.repo.findAll();
    }
}
