package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.models.ShopCartItem;
import com.ecommerce.backend.services.ShopCartService;

@RestController
@CrossOrigin
@RequestMapping("/shop-cart")
public class ShopCartController {

    private final ShopCartService cartService;

    public ShopCartController(ShopCartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/web-user-id={webUserId}/shop-product-id={productId}/quantity={quantity}")
    public ResponseEntity<ShopCart> addProductToCart(
            @PathVariable Integer webUserId,
            @PathVariable Integer productId,
            @PathVariable Integer quantity
    ) {
        ShopCart cart = cartService.addProduct(webUserId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/web-user-id={webUserId}")
    public boolean purcharseCart(@PathVariable Integer webUserId) {
        ShopCart currentCart = this.cartService.getOrCreateCart(webUserId);
        if (this.cartService.tryToBuyItems(currentCart)) {
            this.cartService.prepareOrder(currentCart);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay stock manin");
    }

    @GetMapping("/web-user-id={id}")
    public List<ShopCartItem> getCurrentCart(@PathVariable("id") Integer webUserId) {
        ShopCart currentCart = cartService.getOrCreateCart(webUserId);
        return currentCart.getItems();
    }

}
