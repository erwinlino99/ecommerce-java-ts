package com.ecommerce.backend.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.ShopIndex;
import com.ecommerce.backend.repositories.ShopIndexRepository;

@Service
public class ShopIndexService {

    private final ShopIndexRepository shopIndexRepository;

    public ShopIndexService(ShopIndexRepository shopIndexRepository) {
        this.shopIndexRepository = shopIndexRepository;
    }

    public List<ShopIndex> getAll() {
        return shopIndexRepository.findAll();
    }
}