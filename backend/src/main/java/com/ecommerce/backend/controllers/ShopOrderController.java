package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.dto.ShopOrderDto;
import com.ecommerce.backend.repositories.WebUserRepository;
import com.ecommerce.backend.services.ShopOrderService;

@RestController
@RequestMapping("/shop-order")
public class ShopOrderController {

    private final ShopOrderService orderService;
    private final WebUserRepository webUserRepo;

    public ShopOrderController(ShopOrderService orderService,WebUserRepository webUserRepo) {
        this.orderService = orderService;
        this.webUserRepo=webUserRepo;
    }

    @GetMapping("/from-all-web-users")
    public List<ShopOrderDto> getAllShopOrders() {
        return this.orderService.getAllShopOrdersDto();
    }

    @GetMapping("/shop-orders")
    public List<ShopOrderDto> getAllShopOrdersbyWebUser(Authentication auth) {
        String email=auth.getName();
              Integer webUserId = (int) webUserRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();
        return this.orderService.getAllShopOrdersbyWebUserId(webUserId);
    }
}
