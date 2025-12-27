package com.ecommerce.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ShopOrderDto(Integer id, Double totalAmount, List<ShopOrderItemDto> items,
                String shopOrderStatusName , LocalDateTime created) {

}
