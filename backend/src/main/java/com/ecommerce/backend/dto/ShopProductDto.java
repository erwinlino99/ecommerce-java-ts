package com.ecommerce.backend.dto;

public class ShopProductDto {

    private final Integer id;
    private final String name;
    private final double price;
    private final String measurementName;
    private final Integer measurementUnit;

    public ShopProductDto(Integer id, String name, double price, String measurementName, Integer measurementUnit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.measurementName = measurementName;
        this.measurementUnit = measurementUnit;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public Integer getMeasurementUnit() {
        return measurementUnit;
    }
}
