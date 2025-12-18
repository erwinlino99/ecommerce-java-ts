package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.ShopOrderDto;
import com.ecommerce.backend.services.ShopOrderService;

@RestController
@RequestMapping("/shop-order")
public class ShopOrderController {

    private final ShopOrderService service;

    public ShopOrderController(ShopOrderService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<ShopOrderDto> getAllShopOrders() {
        return this.service.getAllShopOrdersDto();
    }

    @GetMapping("/user/{webUserId}")
    public List<ShopOrderDto> getAllShopOrdersbyWebUserId(@PathVariable Integer webUserId) {
        return this.service.getAllShopOrdersbyWebUserId(webUserId);
    }
}
