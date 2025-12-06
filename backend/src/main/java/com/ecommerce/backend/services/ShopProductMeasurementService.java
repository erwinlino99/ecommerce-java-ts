package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.ShopProductMeasurement;
import com.ecommerce.backend.repositories.ShopProductMeasurementRepository;
@Service
public class ShopProductMeasurementService {

    private final ShopProductMeasurementRepository repo;

    public ShopProductMeasurementService(ShopProductMeasurementRepository repo) {
        this.repo = repo;
    }

    public List<ShopProductMeasurement> getAllRecord() {
        return this.repo.findAll();
    }
}
