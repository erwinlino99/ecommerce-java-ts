package com.ecommerce.backend.dto.mapper;

import java.util.List;

import com.ecommerce.backend.dto.CpShopOrderDto;
import com.ecommerce.backend.dto.ShopOrderDto;
import com.ecommerce.backend.dto.ShopOrderItemDto;
import com.ecommerce.backend.models.ShopOrder;

public class ShopOrderMapper {

    public static ShopOrderDto toDto(ShopOrder shopOrder) {
        // PRIMERO DEVOLVEMOS TODOS LOS ITEMS EN FORMATO DE DTO
        List<ShopOrderItemDto> itemsDto = shopOrder.getItems().stream()
                .map(ShopOrderItemMapper::toDto).toList();

        return new ShopOrderDto(shopOrder.getId(), shopOrder.getTotalAmount(), itemsDto,
                shopOrder.getShopOrderStatus().getName(),
                shopOrder.getCreated(), shopOrder.getGift());
    }

    public static CpShopOrderDto toCpDto(ShopOrder shopOrder) {
        List<ShopOrderItemDto> itemsDto = shopOrder.getItems().stream()
                .map(ShopOrderItemMapper::toDto)
                .toList();
                
        String userName = shopOrder.getWebUser().getName();
        String userEmail = shopOrder.getWebUser().getEmail();
        return new CpShopOrderDto(
                shopOrder.getId(),
                shopOrder.getTotalAmount(),
                userName,
                userEmail,
                itemsDto,
                shopOrder.getShopOrderStatus().getName(),
                shopOrder.getCreated());
    }
}
