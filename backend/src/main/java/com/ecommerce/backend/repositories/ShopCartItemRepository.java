package com.ecommerce.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopCartItem;

@Repository
public interface ShopCartItemRepository extends JpaRepository<ShopCartItem, Integer> {
    
      Optional<ShopCartItem> findByShopCart_IdAndShopProduct_Id(Integer shopCartId, Integer shopProductId);



}
