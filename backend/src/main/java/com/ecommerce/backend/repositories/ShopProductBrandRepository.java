package com.ecommerce.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.models.ShopProductBrand;

public interface ShopProductBrandRepository extends JpaRepository<ShopProductBrand, Integer> {

   Optional<ShopProductBrand> findByNameIgnoreCase(String name);
}
