package com.ecommerce.backend.dto.mapper;

import com.ecommerce.backend.dto.ShopOrderItemDto;
import com.ecommerce.backend.dto.ShopProductLiteDto;
import com.ecommerce.backend.models.ShopOrderItem;

public class ShopOrderItemMapper {

    public static ShopOrderItemDto toDto(ShopOrderItem item) {
        // FIRST MAKE A SHOPRODUCT LITE FOR PARAMETER
        ShopProductLiteDto product = ShoProductMapper.toDto(item.getShopProduct());
        return new ShopOrderItemDto(product, item.getQuantity(), item.getUnitPrice(), item.getSubtotal());

    }

}
