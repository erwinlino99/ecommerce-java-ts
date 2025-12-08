package com.ecommerce.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.services.ShopCartService;

@RestController
@CrossOrigin
@RequestMapping("/shop-cart")
public class ShopCartController {

    private final ShopCartService cartService;

    public ShopCartController(ShopCartService cartService) {
        this.cartService = cartService;
    }

    // POST /shop-cart/{webUserId}/add/{productId}?quantity=7
    @PostMapping("/{webUserId}/add/{productId}")
    public ResponseEntity<ShopCart> addProductToCart(
            @PathVariable Integer webUserId,
            @PathVariable Integer productId,
            @RequestParam(defaultValue = "1") Integer quantity
    ) {
        ShopCart cart = cartService.addProduct(webUserId, productId, quantity);
        return ResponseEntity.ok(cart);
    }
}
