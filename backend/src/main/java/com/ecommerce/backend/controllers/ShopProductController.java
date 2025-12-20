package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.ShopProductDto;
import com.ecommerce.backend.services.ShopProductService;

@RestController
@CrossOrigin
public class ShopProductController {

    private final ShopProductService service;

    public ShopProductController(ShopProductService service) {
        this.service = service;
    }

    @GetMapping("/all-shop-products")
    public List<ShopProductDto> getAll() {
        return this.service.getAllRecords();
    }

    @GetMapping("/shop-product-id={shopProductId}")
    public ShopProductDto getShopProductById(@PathVariable Integer shopProductId) {
        return this.service.getShopProductById(shopProductId);
    }


}
