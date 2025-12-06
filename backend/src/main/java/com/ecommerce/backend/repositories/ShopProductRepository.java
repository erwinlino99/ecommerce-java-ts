package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopProduct;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct,Integer>{
    
}
