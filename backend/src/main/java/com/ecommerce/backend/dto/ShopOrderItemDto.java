package com.ecommerce.backend.dto;

public class ShopOrderItemDto {

    private ShopProductLiteDto product;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;

    public ShopOrderItemDto(ShopProductLiteDto product, Integer quantity, Double unitPrice, Double subtotal) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;

    }

    public void setProductDto(ShopProductLiteDto product) {
        this.product = product;
    }

    public ShopProductLiteDto getProductDto() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

}
