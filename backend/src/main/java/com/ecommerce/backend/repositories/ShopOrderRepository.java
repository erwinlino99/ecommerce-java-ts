package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopOrder;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer>{
    
}
