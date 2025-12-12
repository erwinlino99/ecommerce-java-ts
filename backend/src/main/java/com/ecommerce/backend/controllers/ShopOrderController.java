package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.ShopOrder;
import com.ecommerce.backend.services.ShopOrderService;

@RestController
@RequestMapping("/shop-order")
public class ShopOrderController {

    private ShopOrderService service;

    public ShopOrderController(ShopOrderService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<ShopOrder> getAllShopOrders() {
        return this.service.getAllShopOrders();
    }

}
