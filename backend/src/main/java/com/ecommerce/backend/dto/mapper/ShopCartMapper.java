package com.ecommerce.backend.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.backend.dto.ShopCartDto;
import com.ecommerce.backend.dto.ShopCartItemDto;
import com.ecommerce.backend.models.ShopCart;

public class ShopCartMapper {

    public static ShopCartDto toDto(ShopCart cart) {
        List<ShopCartItemDto> items = cart.getItems().stream()
                .map(i -> new ShopCartItemDto(
                i.getShopProductId(),
                i.getProductName(),
                i.getUnitPrice(),
                i.getQuantity(),
                i.getSubtotal()
        ))
                .collect(Collectors.toList());

        return new ShopCartDto(
                cart.getId(),
                cart.getWebUserId(),
                cart.getTotalAmount(),
                cart.getTotalItems(),
                items
        );
    }
}
