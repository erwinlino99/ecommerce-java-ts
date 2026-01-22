package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.ShopProductDto;
import com.ecommerce.backend.dto.request.ShopProductRequest;
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

    @GetMapping("/shop-product-id/{shopProductId}")
    public ShopProductDto getShopProductById(@PathVariable Integer shopProductId) {
        return this.service.getShopProductById(shopProductId);
    }

    @PutMapping("/shop-product-id/{shopProductId}")
    public ShopProductDto updateShopProduct(@PathVariable Integer shopProductId,
            @RequestBody ShopProductRequest shopProductRequest) {
        return this.service.updateShopProduct(shopProductId, shopProductRequest);
    }

    @DeleteMapping("/shop-product/delete/id={shopProductId}")
    public ResponseEntity deletedShopProduct(@PathVariable Integer shopProductId) {
        return this.service.deletedShopProduct(shopProductId);
    }
    @PostMapping("/shop-product/restore/{shopProductId}")
    public ResponseEntity restoreShopProduct(@PathVariable Integer shopProductId) {
        return this.service.restoreShopProduct(shopProductId);
    }
}
