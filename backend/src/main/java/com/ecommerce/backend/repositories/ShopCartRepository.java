package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopCart;
@Repository
public interface ShopCartRepository extends JpaRepository<ShopCart,Integer>{
    
}
