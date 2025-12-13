package com.ecommerce.backend.dto;

public class ShopProductLiteDto {

    private Integer id;
    private String name;
    private String measurementName;
    private Integer measurementUnit;

    public ShopProductLiteDto(Integer id, String name, String measurementName, Integer measurementUnit) {
        this.id = id;
        this.name = name;
        this.measurementName = measurementName;
        this.measurementUnit = measurementUnit;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public Integer getMeasurementUnit() {
        return measurementUnit;
    }
}
