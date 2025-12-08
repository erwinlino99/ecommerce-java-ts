package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopCartItem;

@Repository
public interface ShopCartItemRepository extends JpaRepository<ShopCartItem, Integer> {
    
}
