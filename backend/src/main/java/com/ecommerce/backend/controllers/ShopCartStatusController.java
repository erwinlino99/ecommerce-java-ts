package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.ShopCartStatus;
import com.ecommerce.backend.services.ShopCartStatusService;

@RestController
@RequestMapping("/shop_cart_status")
@CrossOrigin
public class ShopCartStatusController {

    private final ShopCartStatusService service;

    public ShopCartStatusController(ShopCartStatusService service){
        this.service=service;
    }

    @GetMapping
    public List<ShopCartStatus> getAll(){
        return this.service.getAllRecords();
    }

    
}
