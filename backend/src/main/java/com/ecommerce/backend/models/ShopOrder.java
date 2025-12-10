package com.ecommerce.backend.models;

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
@Table(name = "shop_order")
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "web_user_id", nullable = false)
    private NewWebUser webUser;

    @ManyToOne
    @JoinColumn(name = "shop_oder_status_id", nullable = false)
    private ShopOrderStatus shopOrderStatus;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "total_items", nullable = false)
    private Integer totalItems;

    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ShopOrderItem> shopOrderItems = new ArrayList();

    public ShopOrder() {

    }

    public NewWebUser getWebUser() {
        return this.webUser;
    }

    public ShopOrderStatus getShopOrderStatus() {
        return this.shopOrderStatus;
    }

    public List<ShopOrderItem> getItems() {
        return this.shopOrderItems;
    }

}
