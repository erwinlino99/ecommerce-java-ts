package com.ecommerce.backend.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.ShopCartDto;
import com.ecommerce.backend.dto.mapper.ShopCartMapper;
import com.ecommerce.backend.dto.request.AddOrReduceToCartRequest;
import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.services.ShopCartService;
import com.ecommerce.backend.services.WebUserService;
import com.ecommerce.backend.util.UseLogger;

@RestController
@CrossOrigin
@RequestMapping("/shop-cart")
public class ShopCartController {

    private final ShopCartService cartService;
    private final WebUserService webUserService;

    public ShopCartController(ShopCartService cartService, WebUserService webUserService) {
        this.cartService = cartService;
        this.webUserService = webUserService;
    }

    @GetMapping("/cart")
    public ShopCartDto getCart(Authentication auth) {
        Integer webUserId = this.webUserService.getWebUserId(auth);
        ShopCart currentCart = cartService.getOrCreateCart(webUserId);
        return ShopCartMapper.toDto(currentCart);
    }

    @PostMapping("/add")
    public ResponseEntity<ShopCart> addProductToCart(Authentication auth, @RequestBody AddOrReduceToCartRequest body) {
        Integer webUserId = this.webUserService.getWebUserId(auth);
        ShopCart cart = cartService.addOrReduceShopProduct(webUserId, body.getShopProductId(), body.getQuantity(),
                body.getAction());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/decrease")
    public ResponseEntity<ShopCart> decreateShopProductToCart(Authentication auth,
            @RequestBody AddOrReduceToCartRequest body) {
        Integer webUserId = this.webUserService.getWebUserId(auth);
        ShopCart cart = cartService.addOrReduceShopProduct(webUserId, body.getShopProductId(), body.getQuantity(),
                body.getAction());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/empty-shop-cart-item")
    public ResponseEntity<ShopCart> emptyShopCartItem(Authentication auth, @RequestBody AddOrReduceToCartRequest body) {
        Integer webUserId = this.webUserService.getWebUserId(auth);
        ShopCart cart = cartService.emptyShopCartItem(webUserId, body.getShopProductId());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/empty")
    public ResponseEntity emptyCurrentCart(Authentication auth) {
        Integer webUserId = this.webUserService.getWebUserId(auth);
        ShopCart currentCart = this.cartService.getOrCreateCart(webUserId);
        this.cartService.emptyCurrentCart(currentCart);
        return ResponseEntity.ok(Map.of(200, "PRODUCTOS ELIMINADOS"));
    }

    @PostMapping("/purchase") 
    public ResponseEntity purchaseCart(Authentication auth) {
        Integer webUserId = this.webUserService.getWebUserId(auth);
        ShopCart currentCart = this.cartService.getOrCreateCart(webUserId);
        this.cartService.tryToBuyItems(currentCart);
        this.cartService.prepareOrder(currentCart);
        this.cartService.emptyCurrentCart(currentCart);
        return ResponseEntity.ok(Map.of("200", "COMPRA REALIZADA"));
    }

}
