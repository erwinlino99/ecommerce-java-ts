package com.ecommerce.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.dto.AddToCartDto;
import com.ecommerce.backend.dto.ShopCartDto;
import com.ecommerce.backend.dto.mapper.ShopCartMapper;
import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.repositories.WebUserRepository;
import com.ecommerce.backend.services.ShopCartService;
import com.ecommerce.backend.util.UseLogger;

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

    @PostMapping("/add")
    public ResponseEntity<ShopCart> addProductToCart(
            Authentication auth,
            @RequestBody AddToCartDto body
    ) {

        String email = auth.getName();
        UseLogger.info("EMAIL VALUE -->", email);
        Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();

        ShopCart cart = cartService.addProduct(webUserId, body.getShopProductId(), body.getQuantity());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/web-user-id={webUserId}")
    public boolean purcharseCart(@PathVariable Integer webUserId) {
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

    @GetMapping("/cart")
    public ShopCartDto getCart(Authentication auth) {
        String email = auth.getName();
        Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();
        ShopCart currentCart = cartService.getOrCreateCart(webUserId);
        return ShopCartMapper.toDto(currentCart);
    }

}
