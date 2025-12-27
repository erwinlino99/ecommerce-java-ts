package com.ecommerce.backend.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.dto.ShopOrderDto;
import com.ecommerce.backend.dto.mapper.ShopOrderMapper;
import com.ecommerce.backend.models.ShopOrder;
import com.ecommerce.backend.models.ShopOrderItem;
import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.repositories.ShopOrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShopOrderService {

    private final ShopOrderRepository shopOrderRepo;
    private final ShopCartService shopCartService;

    public ShopOrderService(ShopOrderRepository shopOrderRepo, ShopCartService shopCartService) {
        this.shopOrderRepo = shopOrderRepo;
        this.shopCartService = shopCartService;
    }

    // FROM ALL WEB USERS
    @Transactional(readOnly = true)
    public List<ShopOrderDto> getAllShopOrdersDto() {
        return shopOrderRepo.findAll().stream().map(ShopOrderMapper::toDto).toList();
    }

    public List<ShopOrderDto> getAllShopOrdersbyWebUserId(Integer webUserId) {
        return shopOrderRepo.findByWebUserId(webUserId).stream().map(ShopOrderMapper::toDto).toList();
    }

    @Transactional
    public ResponseEntity repeatShopOrder(Integer webUserId, Integer ShopOrderId) {
        ShopOrder shopOrder = shopOrderRepo.findById(ShopOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + ShopOrderId));
                
        for (ShopOrderItem item : shopOrder.getItems()) {
            ShopProduct shopProduct = item.getShopProduct();
            // LLAMAOS AL SERVICIO DEL CARRO Y AGREGAMOS LA CANTIDAD Y EL PRODUCTO
            this.shopCartService.addOrReduceShopProduct(webUserId, shopProduct.getId(), item.getQuantity(), true);
        }
        return ResponseEntity.ok(Map.of("message", "PRODUCTOS AGREGADOS "));
    }

}
