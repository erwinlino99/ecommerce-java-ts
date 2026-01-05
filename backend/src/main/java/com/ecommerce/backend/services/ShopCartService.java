package com.ecommerce.backend.services;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.request.AdjustShopCartItemRequest;
import com.ecommerce.backend.dto.response.OutOfStockProductResponse;
import com.ecommerce.backend.exceptions.OutOfStockException;
import com.ecommerce.backend.models.ShopCart;
import com.ecommerce.backend.models.ShopCartItem;
import com.ecommerce.backend.models.ShopCartStatus;
import com.ecommerce.backend.models.ShopOrder;
import com.ecommerce.backend.models.ShopOrderItem;
import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.repositories.ShopCartItemRepository;
import com.ecommerce.backend.repositories.ShopCartRepository;
import com.ecommerce.backend.repositories.ShopOrderRepository;
import com.ecommerce.backend.repositories.ShopProductRepository;
import com.ecommerce.backend.repositories.WebUserRepository;
import com.ecommerce.backend.util.UseLogger;

import jakarta.transaction.Transactional;

@Service
public class ShopCartService {

    private final ShopCartRepository cartRepo;
    private final ShopCartItemRepository cartItemRepo;
    private final ShopProductRepository shopProductRepo;
    private final WebUserRepository webUserRepo;
    private final ShopOrderRepository orderRepo;
    private final PokeApiService pokeApiService;

    public ShopCartService(ShopCartRepository cartRepo, ShopCartItemRepository cartItemRepo,
            ShopProductRepository shopProductRepo, WebUserRepository webUserRepo,
            ShopOrderRepository orderRepo, PokeApiService pokeApiService) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.shopProductRepo = shopProductRepo;
        this.webUserRepo = webUserRepo;
        this.orderRepo = orderRepo;
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
    public void tryToBuyItems(ShopCart cart) {

        List<OutOfStockProductResponse> errors = new ArrayList();

        List<ShopCartItem> items = cart.getItems();
        for (ShopCartItem item : items) {
            ShopProduct product = item.getShopProduct();
            Integer currentStock = product.getCurrentStock();
            Integer quantity = item.getQuantity();
            if (quantity > currentStock) {
                errors.add(new OutOfStockProductResponse(product.getId(), item.getProductName(), quantity));
            }
        }
        if (!errors.isEmpty()) {
            throw new OutOfStockException(errors);
        }
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
                this.updateCurrentStock(cartItem.getShopProduct(), cartItem.getQuantity());
            }
            newShopOrder.setItems(orderItems);
            orderRepo.save(newShopOrder);
            return true;
        }
        return false;
    }

    private void updateCurrentStock(ShopProduct product, Integer quantityOrdered) {
        int newStock = product.getCurrentStock() - quantityOrdered;
        if (newStock < 0) {
            throw new RuntimeException("Stock insuficiente para el producto " + product.getName());
        }
        product.setCurrentStock(newStock);
        this.shopProductRepo.save(product);
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

    // @Transactional
    // public void adjustCurrentCart(Integer webUserId, ShopCart currentCart,
    // List<AdjustShopCartItemRequest> adjustItems) {
    // List<ShopCartItem> cartItems = currentCart.getItems();
    // // LISTA TEMPORTAL DE LOS ITEMS A BORRAR
    // List<ShopCartItem> toDelete = new ArrayList<>();
    // for (ShopCartItem item : cartItems) {
    // Integer itemCartProductId = item.getShopProductId();
    // for (AdjustShopCartItemRequest productToFix : adjustItems) {
    // if (Objects.equals(itemCartProductId, productToFix.shopProductId())) {
    // // UseLogger.info("MARCADO PARA ELIMINAR", itemCartProductId);
    // toDelete.add(item);
    // break;
    // }
    // }
    // }
    // if (!toDelete.isEmpty()) {
    // cartItems.removeAll(toDelete);
    // cartItemRepo.deleteAll(toDelete);
    // }
    // this.recalculateShopCart(currentCart);
    // cartRepo.save(currentCart);

    // for (AdjustShopCartItemRequest item : adjustItems) {
    // // AGREGAMOS EL PRODUCTO CON EL MAXIMO DE STOCK
    // ShopProduct product = this.shopProductRepo.getById(item.shopProductId());
    // this.addOrReduceShopProduct(webUserId, product.getId(),
    // product.getCurrentStock(), true);
    // }
    // }

    @Transactional
    public void adjustCurrentCart(Integer webUserId, ShopCart currentCart,
            List<AdjustShopCartItemRequest> adjustItems) {

        // 1. Obtener IDs únicos
        Set<Integer> idsToAdjust = adjustItems.stream()
                .map(AdjustShopCartItemRequest::shopProductId)
                .collect(Collectors.toSet());

        // 2. Eliminar los items actuales que fallaron del carrito
        List<ShopCartItem> itemsToRemove = currentCart.getItems().stream()
                .filter(item -> idsToAdjust.contains(item.getShopProductId()))
                .collect(Collectors.toList());

        if (!itemsToRemove.isEmpty()) {
            currentCart.getItems().removeAll(itemsToRemove);
            cartItemRepo.deleteAllInBatch(itemsToRemove);
        }

        List<ShopProduct> productsWithStock = shopProductRepo.findAllById(idsToAdjust).stream()
                .filter(product -> product.getCurrentStock() > 0) // <--- CLAVE: Solo si hay stock
                .toList();

        for (ShopProduct product : productsWithStock) {
            this.addOrReduceShopProduct(webUserId, product.getId(), product.getCurrentStock(), true);
        }
        this.recalculateShopCart(currentCart);
        cartRepo.save(currentCart);
    }
}
