package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.SelectableDto;
import com.ecommerce.backend.services.ShopProductBrandService;

@RestController
@RequestMapping("/shop-product-brand")
public class ShopProductBrandController {

    public ShopProductBrandService service;

    public ShopProductBrandController(ShopProductBrandService service){
        this.service=service;
    }

    @GetMapping("/selectable")
    public List<SelectableDto> getSelectable() {
        return this.service.getSelectable();
    }
}
