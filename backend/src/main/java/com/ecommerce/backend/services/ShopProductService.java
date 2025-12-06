package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.repositories.ShopProductRepository;


@Service
public class ShopProductService {
    private final ShopProductRepository repo;

    public ShopProductService(ShopProductRepository repo){
        this.repo=repo;
    }

    public List<ShopProduct> getAllRecords(){
        return this.repo.findAll();
    }
}
