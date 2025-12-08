package com.ecommerce.backend.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.models.ShopCartItem;
import com.ecommerce.backend.models.ShopCartStatus;
import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.repositories.NewWebUserRepository;
import com.ecommerce.backend.repositories.ShopCartItemRepository;
import com.ecommerce.backend.repositories.ShopCartRepository;
import com.ecommerce.backend.repositories.ShopProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ShopCartService {

    private final ShopCartRepository cartRepo;
    private final ShopCartItemRepository cartItemRepo;
    private final ShopProductRepository shopProductRepo;
    private final NewWebUserRepository webUserRepo;

    public ShopCartService(ShopCartRepository cartRepo, ShopCartItemRepository cartItemRepo, ShopProductRepository shopProductRepo, NewWebUserRepository webUserRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.shopProductRepo = shopProductRepo;
        this.webUserRepo = webUserRepo;
    }

    @Transactional
    public ShopCart getOrCreateCart(Integer webUserId) {

        return cartRepo.findFirstByWebUser_Id(webUserId)
                .orElseGet(() -> {
                    ShopCart cart = new ShopCart();
                    var user = this.webUserRepo.findById(webUserId)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    cart.setWebUser(user);
                    cart.setTotalItems(0);
                    cart.setTotalAmount(0.0);

                    ShopCartStatus status = new ShopCartStatus();
                    status.setId(1);
                    cart.setShopCartStatus(status);

                    cart.setCreated(LocalDateTime.now());
                    return cartRepo.save(cart);
                });
    }

    @Transactional
    public ShopCart addProduct(Integer webUserId, Integer shopProductId, int quantity) {
        ShopCart cart = getOrCreateCart(webUserId);

        ShopProduct product = shopProductRepo.findById(shopProductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ShopCartItem item = cartItemRepo
                .findByShopCart_IdAndShopProduct_Id(cart.getId(), shopProductId)
                .orElseGet(() -> {
                    ShopCartItem newItem = new ShopCartItem();
                    newItem.setShopCart(cart);
                    newItem.setShopProduct(product);
                    newItem.setQuantity(0);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + quantity);
        cartItemRepo.save(item);

        return cart;
    }

}
