package com.ecommerce.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_product")
public class ShopProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "short_description")
    private String shortDescription;

    @Column
    private String description;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @Column
    private LocalDateTime deleted;

    @Column(name = "shop_product_brand_id")
    private Integer shopProductBrandId;

    // @ManyToOne
    // @JoinColumn(name="shop_product_brand_id")
    // private ShopBrand brand;

    // @Column(name = "shop_product_measurement_id")
    // private Integer shopProductMeasurementId;

    @ManyToOne
    @JoinColumn(name="shop_product_measurement_id")
    private ShopProductMeasurement measurement;

    @Column(name = "current_stock")
    private Integer currentStock;

    @Column
    private double price;

    public ShopProduct() {
    }

    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }

    // GETTERS Y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return shortDescription;
    }

    public void setShort_description(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
    }

    public Integer getShopProductBrandId() {
        return shopProductBrandId;
    }

    public void setShopProductBrandId(Integer shopProductBrandId) {
        this.shopProductBrandId = shopProductBrandId;
    }

    public ShopProductMeasurement getShopProductMeasurementId() {
        return this.measurement;
    }

    public void setShopProductMeasurementId(ShopProductMeasurement measurement) {
        this.measurement = measurement;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
