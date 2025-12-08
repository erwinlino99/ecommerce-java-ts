package com.ecommerce.backend.services;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(ShopCartService.class);
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

        ShopCart currentCart = getOrCreateCart(webUserId);
        ShopProduct product = shopProductRepo.findById(shopProductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        log.info("PRODUCT ADDED : {}", product.toString());
        ShopCartItem item = cartItemRepo
                .findByShopCart_IdAndShopProduct_Id(currentCart.getId(), shopProductId)
                .orElseGet(() -> {
                    ShopCartItem shopCartItem = new ShopCartItem();
                    shopCartItem.setShopCart(currentCart);
                    shopCartItem.setShopProduct(product);
                    shopCartItem.setQuantity(0);
                    shopCartItem.setUnitPrice(product.getPrice());
                    currentCart.getItems().add(shopCartItem);
                    return shopCartItem;
                });
        item.setQuantity(item.getQuantity() + quantity);
        item.setSubtotal(item.getQuantity() * product.getPrice());
        //VOLVEMOS A RECALCULAR EL CARRO
        this.recalculateShopCart(currentCart);
        cartItemRepo.save(item);
        return currentCart;
    }

    private void recalculateShopCart(ShopCart shopCart) {
        int totalItems = 0;
        double totalAmount = 0;
        for (ShopCartItem item : shopCart.getItems()) {
            totalItems = totalItems + item.getQuantity();
            totalAmount = totalAmount + item.getSubtotal();
        }
        shopCart.setTotalAmount(totalAmount);
        shopCart.setTotalItems(totalItems);
    }

}
