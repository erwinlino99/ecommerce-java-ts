package com.ecommerce.backend.dto;

public class AddToCartDto {

    private Integer shopProductId;
    private Integer quantity;

    public AddToCartDto() {

    }

    public Integer getShopProductId() {
        return shopProductId;
    }

    public void setShopProductId(Integer shopProductId) {
        this.shopProductId = shopProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
