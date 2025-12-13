package com.ecommerce.backend.dto;

public class ShopOrderStatusDto {

    private String name;

    public ShopOrderStatusDto(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
}