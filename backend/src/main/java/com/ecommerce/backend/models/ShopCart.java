package com.ecommerce.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_cart")
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Integer getId() {
        return this.id;
    }

    public Integer getWebUserId() {
        return this.webUser.getId();
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }
    public LocalDateTime getCreated(){
        return this.created;
    }
    public String getCartStatus(){
        return this.shopCartStatus.getName();
    }

}
