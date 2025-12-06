package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopProductMeasurement;

@Repository
public interface ShopProductMeasurementRepository extends JpaRepository<ShopProductMeasurement,Integer>{
    
}
