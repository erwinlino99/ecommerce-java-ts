package com.ecommerce.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.dto.ShopCartDto;
import com.ecommerce.backend.dto.mapper.ShopCartMapper;
import com.ecommerce.backend.dto.request.AddOrReduceToCartRequest;
import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.repositories.WebUserRepository;
import com.ecommerce.backend.services.ShopCartService;

@RestController
@CrossOrigin
@RequestMapping("/shop-cart")
public class ShopCartController {

    private final ShopCartService cartService;
    private final WebUserRepository webUserRepo;

    public ShopCartController(ShopCartService cartService, WebUserRepository webUserRepo) {
        this.cartService = cartService;
        this.webUserRepo = webUserRepo;
    }

    @GetMapping("/cart")
    public ShopCartDto getCart(Authentication auth) {
        String email = auth.getName();
        Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();
        ShopCart currentCart = cartService.getOrCreateCart(webUserId);
        return ShopCartMapper.toDto(currentCart);
    }

    @PostMapping("/add")
    public ResponseEntity<ShopCart> addProductToCart(Authentication auth, @RequestBody AddOrReduceToCartRequest body) {

        String email = auth.getName();
        Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();

        ShopCart cart = cartService.addOrReduceShopProduct(webUserId, body.getShopProductId(), body.getQuantity(),
                body.getAction());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/decrease")
    public ResponseEntity<ShopCart> decreateShopProductToCart(Authentication auth,
            @RequestBody AddOrReduceToCartRequest body) {

        String email = auth.getName();
        Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();

        ShopCart cart = cartService.addOrReduceShopProduct(webUserId, body.getShopProductId(), body.getQuantity(),
                body.getAction());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/empty-shop-cart-item")
    public ResponseEntity<ShopCart> emptyShopCartItem(Authentication auth, @RequestBody AddOrReduceToCartRequest body) {

        String email = auth.getName();
        Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();
        ShopCart cart = cartService.emptyShopCartItem(webUserId, body.getShopProductId());

        return ResponseEntity.ok(cart);
    }

    @PostMapping("/purcharse")
    public boolean purcharseCart(Authentication auth) {
        String email = auth.getName();
        Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();
        ShopCart currentCart = this.cartService.getOrCreateCart(webUserId);
        if (this.cartService.tryToBuyItems(currentCart)) {
            if (this.cartService.prepareOrder(currentCart)) {
                this.cartService.emptyCurrentCart(currentCart);
                return true;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fallo al realizar el pedido");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sin stock para algunos productos");
    }

}
