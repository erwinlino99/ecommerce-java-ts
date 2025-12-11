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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
    @JoinColumn(name = "shop_order_status_id", nullable = false)
    private ShopOrderStatus shopOrderStatus=new ShopOrderStatus();

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "total_items", nullable = false)
    private Integer totalItems;

    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopOrderItem> shopOrderItems = new ArrayList();

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime deleted;

    @Column
    private LocalDateTime modified;

    public ShopOrder() {

    }

    @PrePersist
    public void prePersist() {
        this.shopOrderStatus.setId(1);
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }

    public void setWebUser(NewWebUser webUser) {
        this.webUser = webUser;
    }

    public NewWebUser getWebUser() {
        return this.webUser;
    }

    public ShopOrderStatus getShopOrderStatus() {
        return this.shopOrderStatus;
    }

    public void setShopOrderStatus(ShopOrderStatus shopOrderStatus) {
        this.shopOrderStatus = shopOrderStatus;
    }

    public List<ShopOrderItem> getItems() {
        return this.shopOrderItems;
    }

    public void setItems(List<ShopOrderItem> items) {
        this.shopOrderItems = items;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
