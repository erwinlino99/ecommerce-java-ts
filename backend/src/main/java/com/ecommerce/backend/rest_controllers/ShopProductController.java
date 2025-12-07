package com.ecommerce.backend.rest_controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/shop_product")
    public List<ShopProductDto> getAll() {
        return this.service.getAllRecords();
    }

}
