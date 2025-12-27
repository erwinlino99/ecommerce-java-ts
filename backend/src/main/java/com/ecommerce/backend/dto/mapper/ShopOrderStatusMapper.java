package com.ecommerce.backend.dto.mapper;

import com.ecommerce.backend.dto.ShopOrderStatusDto;
import com.ecommerce.backend.models.ShopOrderStatus;

public class ShopOrderStatusMapper {

    public static ShopOrderStatusDto toDto(ShopOrderStatus shopOrderStatus){
        return new ShopOrderStatusDto(shopOrderStatus.getName());
    }
}
