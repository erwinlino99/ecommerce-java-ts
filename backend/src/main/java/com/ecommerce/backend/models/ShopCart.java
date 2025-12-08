package com.ecommerce.backend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_cart")
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //PONEMOS LA RELACION DE UNO A MUCHOS DE SHOP_CART A SHOP_CART_ITEMS
    @OneToMany(mappedBy = "shopCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopCartItem> shopCartItems = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "web_user_id")
    private NewWebUser webUser;

    @ManyToOne
    @JoinColumn(name = "shop_cart_status_id")
    private ShopCartStatus shopCartStatus;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "total_items")
    private Integer totalItems;

    @Column
    private LocalDateTime created;

    public ShopCart() {

    }

    public List<ShopCartItem> getItems() {
        return this.shopCartItems;
    }

    public Integer getId() {
        return this.id;
    }

    public NewWebUser getWebUser() {
        return this.webUser;
    }

    public void setWebUser(NewWebUser webUser) {
        this.webUser = webUser;
    }

    public ShopCartStatus getShopCartStatus() {
        return shopCartStatus;
    }

    public void setShopCartStatus(ShopCartStatus shopCartStatus) {
        this.shopCartStatus = shopCartStatus;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    // Helpers opcionales
    public Integer getWebUserId() {
        return webUser != null ? webUser.getId() : null;
    }

    public String getCartStatus() {
        return shopCartStatus != null ? shopCartStatus.getName() : null;
    }
}
