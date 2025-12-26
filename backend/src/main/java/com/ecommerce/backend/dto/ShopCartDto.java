package com.ecommerce.backend.dto;

import java.util.List;

public record ShopCartDto(  Integer shopCartId, Integer webUserId,Double totalAmount,Integer totalItems, List<ShopCartItemDto> items) {

}
