package com.ecommerce.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShopOrder;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {

    List<ShopOrder> findByWebUserId(Integer webUserId);

}
