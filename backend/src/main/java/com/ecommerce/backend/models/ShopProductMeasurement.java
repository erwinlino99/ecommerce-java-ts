package com.ecommerce.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_product_measurement")
public class ShopProductMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //Por que tiene que ser Integer y no int/ Int ? 
    @Column
    private String name; // Es necesario que despues de la anotacion de COlumn este tipado fuertemente con (length = 150, nullable = false) ??
    @Column
    private String description;
    @Column
    private Integer unit;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @Column
    private LocalDateTime deleted;

    public ShopProductMeasurement() {

    }

    //IMPORTANTE ->
    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getUnit() {
        return this.unit;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeleted() {
        return this.deleted;
    }

}
