package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.models.ShopCartStatus;

public interface ShopCartStatusRepository extends JpaRepository<ShopCartStatus,Integer> {

    
}