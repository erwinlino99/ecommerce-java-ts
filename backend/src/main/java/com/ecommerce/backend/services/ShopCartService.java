package com.ecommerce.backend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.models.ShopCartItem;
import com.ecommerce.backend.models.ShopCartStatus;
import com.ecommerce.backend.models.ShopOrder;
import com.ecommerce.backend.models.ShopOrderItem;
import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.repositories.ShopCartItemRepository;
import com.ecommerce.backend.repositories.ShopCartRepository;
import com.ecommerce.backend.repositories.ShopOrderItemRepository;
import com.ecommerce.backend.repositories.ShopOrderRepository;
import com.ecommerce.backend.repositories.ShopProductRepository;
import com.ecommerce.backend.repositories.WebUserRepository;

import jakarta.transaction.Transactional;

@Service
public class ShopCartService {

    private final ShopCartRepository cartRepo;
    private final ShopCartItemRepository cartItemRepo;
    private final ShopProductRepository shopProductRepo;
    private final WebUserRepository webUserRepo;
    private final ShopOrderRepository orderRepo;
    private final ShopOrderItemRepository orderItemRepo;
    private final PokeApiService pokeApiService;

    public ShopCartService(ShopCartRepository cartRepo, ShopCartItemRepository cartItemRepo,
            ShopProductRepository shopProductRepo, WebUserRepository webUserRepo,
            ShopOrderRepository orderRepo, ShopOrderItemRepository orderItemRepo,
            PokeApiService pokeApiService) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.shopProductRepo = shopProductRepo;
        this.webUserRepo = webUserRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.pokeApiService = pokeApiService;
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
    public ShopCart addOrReduceShopProduct(Integer webUserId, Integer shopProductId, int quantity, boolean action) {
        ShopCart currentCart = getOrCreateCart(webUserId);
        ShopProduct product = shopProductRepo.findById(shopProductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ShopCartItem item = cartItemRepo
                .findByShopCart_IdAndShopProduct_Id(currentCart.getId(), shopProductId)
                .orElseGet(() -> {
                    ShopCartItem shopCartItem = new ShopCartItem();
                    shopCartItem.setShopCart(currentCart);
                    shopCartItem.setShopProduct(product);
                    shopCartItem.setQuantity(0);
                    shopCartItem.setUnitPrice(product.getPrice());
                    shopCartItem.setProductName(product.getName());
                    currentCart.getItems().add(shopCartItem);
                    return shopCartItem;
                });
        if (action) {
            item.setQuantity(item.getQuantity() + quantity);
            item.setSubtotal(item.getQuantity() * product.getPrice());
        } else {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - quantity);
                item.setSubtotal(item.getQuantity() * product.getPrice());
            }
        }
        this.recalculateShopCart(currentCart);
        cartItemRepo.save(item);
        return currentCart;
    }

    @Transactional
    public ShopCart emptyShopCartItem(Integer webUserId, Integer shopProductId) {
        ShopCart currentCart = getOrCreateCart(webUserId);
        ShopCartItem itemToDelete = currentCart.getItems().stream()
                .filter(item -> item.getShopProduct().getId().equals(shopProductId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El producto no está en el carrito"));
        currentCart.getItems().remove(itemToDelete);
        // BORRADO DEL ARRAYLIST
        // BORRADO FISICO DE LA BASE DE DATOS
        cartItemRepo.delete(itemToDelete);
        this.recalculateShopCart(currentCart);
        return cartRepo.save(currentCart);
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

    @Transactional
    public boolean tryToBuyItems(ShopCart cart) {
        List<ShopCartItem> items = cart.getItems();
        for (ShopCartItem item : items) {
            ShopProduct product = item.getShopProduct();
            int currentStock = product.getCurrentStock();
            int quantity = item.getQuantity();
            if (quantity > currentStock) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public boolean prepareOrder(ShopCart currentCart) {
        if (!currentCart.getItems().isEmpty()) {
            ShopOrder newShopOrder = new ShopOrder();
            newShopOrder.setWebUser(currentCart.getWebUser());
            newShopOrder.setTotalAmount(currentCart.getTotalAmount());
            newShopOrder.setTotalItems(currentCart.getTotalItems());

            List<ShopOrderItem> orderItems = new ArrayList<>();
            List<ShopCartItem> cartItems = currentCart.getItems();

            var gifts = this.pokeApiService.getRandomPokemonGifts(10);
            newShopOrder.setGift(gifts); 

            for (ShopCartItem cartItem : cartItems) {
                ShopOrderItem orderItem = new ShopOrderItem();
                orderItem.setShopOrder(newShopOrder);
                orderItem.setShopProduct(cartItem.getShopProduct());
                orderItem.setUnitPrice(cartItem.getUnitPrice());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setSubtotal(cartItem.getSubtotal());
                orderItems.add(orderItem);
            }
            newShopOrder.setItems(orderItems);
            orderRepo.save(newShopOrder);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean emptyCurrentCart(ShopCart currentCart) {
        cartItemRepo.deleteAll(currentCart.getItems());
        currentCart.getItems().clear();
        currentCart.setTotalAmount(0.0);
        currentCart.setTotalItems(0);
        cartRepo.save(currentCart);
        return true;
    }
}
