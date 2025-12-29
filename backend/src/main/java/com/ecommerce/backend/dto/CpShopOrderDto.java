package com.ecommerce.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CpShopOrderDto(
        Integer id,
        Double totalAmount,
        String userName,
        String userEmail,
        List<ShopOrderItemDto> items,
        String shopOrderStatusName,
        LocalDateTime created) {

}