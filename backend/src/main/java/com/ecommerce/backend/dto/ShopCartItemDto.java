package com.ecommerce.backend.dto;

public record ShopCartItemDto(
        Integer shopProductId,
        String shopProductName,
        Double unitPrice,
        Integer quantity,
        Double subtotal
        ) {

}
