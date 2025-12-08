package com.ecommerce.backend.controllers;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.ShopProductBrand;

@RestController
public class ShopProductBrandController {

    @GetMapping("by_id")
    public Map<String, Object> getOneBydId() {
        //TODO NEED TO RECOVER PAREMETER FROM FRONTEND IN FUTURE
        ShopProductBrand spb = new ShopProductBrand();
        return spb.getOne(2);
    }

    @GetMapping("shop_product_brand")
    public List<Map<String, Object>> getAll() {
        ShopProductBrand spb = new ShopProductBrand();
        return spb.getAll();
    }
}
