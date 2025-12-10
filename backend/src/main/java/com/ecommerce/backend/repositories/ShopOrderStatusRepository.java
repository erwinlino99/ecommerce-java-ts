package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopOrderStatus;

@Repository
public interface ShopOrderStatusRepository extends JpaRepository<ShopOrderStatus, Integer>{
    
}
