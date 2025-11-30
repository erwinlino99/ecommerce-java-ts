package com.ecommerce.backend.rest_controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.ShopIndex;
import com.ecommerce.backend.services.ShopIndexService;

@RestController
@RequestMapping("/shop_index")
@CrossOrigin // opcional, si llamas desde frontend en otro dominio/puerto
public class ShopIndexController {

    private final ShopIndexService shopIndexService;

    public ShopIndexController(ShopIndexService shopIndexService) {
        this.shopIndexService = shopIndexService;
    }
    
    @GetMapping
    public List<ShopIndex> getAll() {
        return shopIndexService.getAll();
    }
}