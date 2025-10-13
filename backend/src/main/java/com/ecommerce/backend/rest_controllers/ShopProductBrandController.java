package com.ecommerce.backend.rest_controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ShopProductBrandController {

    private final DatabaseConnection dataBase;

    public ShopProductBrandController(DatabaseConnection dataBase) {
        this.dataBase = dataBase;
    }

    @GetMapping("shop_product_brand")
    public List<Map<String,Object>>getAllShopProductBrand(){
        String sql="SELECT * FROM shop_product_brand";
        return this.dataBase.execute().queryForList(sql);
    }
}
