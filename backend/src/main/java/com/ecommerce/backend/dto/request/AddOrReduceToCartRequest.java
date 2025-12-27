package com.ecommerce.backend.dto.request;

public class AddOrReduceToCartRequest {

    private Integer shopProductId;
    private Integer quantity;
    private Boolean action;

    public AddOrReduceToCartRequest() {

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
    public boolean getAction(){
        return this.action;
    }
}
