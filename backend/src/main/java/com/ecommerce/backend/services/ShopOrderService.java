package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.dto.ShopOrderDto;
import com.ecommerce.backend.dto.mapper.ShopOrderMapper;
import com.ecommerce.backend.repositories.ShopOrderRepository;

@Service
public class ShopOrderService {

    private final ShopOrderRepository repo;

    public ShopOrderService(ShopOrderRepository repo) {
        this.repo = repo;
    }

   @Transactional(readOnly = true)
    public List<ShopOrderDto> getAllShopOrdersDto() {
        return repo.findAll().stream().map(ShopOrderMapper::toDto).toList();
    }

    public List<ShopOrderDto> getAllShopOrdersbyWebUserId(Integer webUserId) {
        return repo.findByWebUserId(webUserId).stream().map(ShopOrderMapper::toDto).toList();
    }

}
