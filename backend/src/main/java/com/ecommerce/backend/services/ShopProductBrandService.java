package com.ecommerce.backend.services;

import com.ecommerce.backend.repositories.ShopProductBrandRepository;

public class ShopProductBrandService {

    private ShopProductBrandRepository repo;

    public ShopProductBrandService(ShopProductBrandRepository repo) {
        this.repo = repo;
    }
}
