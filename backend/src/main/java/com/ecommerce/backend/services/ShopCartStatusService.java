package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.ShopCartStatus;
import com.ecommerce.backend.repositories.ShopCartStatusRepository;
@Service
public class ShopCartStatusService {

    private ShopCartStatusRepository repo;

    public ShopCartStatusService(ShopCartStatusRepository repo) {
        this.repo = repo;
    }

    public List<ShopCartStatus> getAllRecords() {
        return this.repo.findAll();
    }

}
