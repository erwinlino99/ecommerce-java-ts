package com.ecommerce.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopCart;

@Repository
public interface ShopCartRepository extends JpaRepository<ShopCart, Integer> {

    Optional<ShopCart> findFirstByWebUser_Id(Integer webUserId);


}
