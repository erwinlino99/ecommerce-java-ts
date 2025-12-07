package com.ecommerce.backend.dto;

import java.time.LocalDateTime;

public class ShopProductDto {

    private final Integer id;
    private final String name;
    private final String description;
    private final String shortDescription;
    private final double price;
    private final String measurementName;
    private final Integer measurementUnit;
    private final LocalDateTime created;
    private final LocalDateTime modified;
    private final LocalDateTime deleted;

    public ShopProductDto(Integer id, String name, String description, String shortDescription, double price, String measurementName, Integer measurementUnit, LocalDateTime created, LocalDateTime modified, LocalDateTime deleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.price = price;
        this.measurementName = measurementName;
        this.measurementUnit = measurementUnit;
        this.created = created;
        this.modified = modified;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getMeasurementName() {
        return this.measurementName;
    }

    public Integer getMeasurementUnit() {
        return this.measurementUnit;
    }

    public String getDescription() {
        return this.description;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }

    public LocalDateTime getDeleted() {
        return this.deleted;
    }

}
