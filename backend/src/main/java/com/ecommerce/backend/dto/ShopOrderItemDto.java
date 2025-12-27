package com.ecommerce.backend.dto;

public record ShopOrderItemDto(ShopProductLiteDto product, Integer quantity, Double unitPrice, Double subtotal) {

}
