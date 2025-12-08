package com.ecommerce.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_cart_status")
public class ShopCartStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private LocalDateTime deleted;

    public ShopCartStatus() {

    }

    public Integer getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getDeleted() {
        return this.deleted;

    }

}
