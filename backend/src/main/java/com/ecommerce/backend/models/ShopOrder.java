package com.ecommerce.backend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.backend.dto.PokemonGiftDto;
import com.ecommerce.backend.dto.mapper.PokemonGiftConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
    private WebUser webUser;

    @ManyToOne
    @JoinColumn(name = "shop_order_status_id", nullable = false)
    private ShopOrderStatus shopOrderStatus = new ShopOrderStatus();

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

    @Column(name = "poke_gift", columnDefinition = "JSON")
    @Convert(converter = PokemonGiftConverter.class)
    private List<PokemonGiftDto> pokeGift;

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

    public Integer getId() {
        return this.id;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public WebUser getWebUser() {
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

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public List<PokemonGiftDto> getGift() {
        return this.pokeGift;
    }

    public void setGift(List<PokemonGiftDto> pokeGift) {
        this.pokeGift = pokeGift;
    }
}
