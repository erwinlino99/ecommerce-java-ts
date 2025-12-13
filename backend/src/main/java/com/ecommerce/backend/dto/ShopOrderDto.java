package com.ecommerce.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ShopOrderDto {

    private Integer id;
    private Double totalAmount;
    private List<ShopOrderItemDto> items;
    private LocalDateTime created;
    private ShopOrderStatusDto shopOrderStatus;

    public ShopOrderDto(Integer id,Double totalAmount, List<ShopOrderItemDto> items, ShopOrderStatusDto shopOrderStatus, LocalDateTime created) {
        this.id=id;
        this.totalAmount = totalAmount;
        this.items = items;
        this.shopOrderStatus = shopOrderStatus;
        this.created = created;

    }

    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }
    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public List<ShopOrderItemDto> getItemsDto() {
        return this.items;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public ShopOrderStatusDto getShopOStatusDto() {
        return this.shopOrderStatus;
    }

}
