package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopIndex;

@Repository
public interface ShopIndexRepository extends JpaRepository<ShopIndex, Long> {
}