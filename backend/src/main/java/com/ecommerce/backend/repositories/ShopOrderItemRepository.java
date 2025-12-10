package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.models.ShopOrderItem;

public interface  ShopOrderItemRepository extends JpaRepository<ShopOrderItem, Integer>{
    
}
