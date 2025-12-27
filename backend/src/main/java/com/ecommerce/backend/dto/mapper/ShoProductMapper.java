package com.ecommerce.backend.dto.mapper;

import com.ecommerce.backend.dto.ShopProductLiteDto;
import com.ecommerce.backend.models.ShopProduct;

public class ShoProductMapper {
    // HACER EL toDto del la version normal / completa para el ECOMMERCE









    public static ShopProductLiteDto toDto(ShopProduct product) {
        if (product == null) {
            return null;
        }
        return new ShopProductLiteDto(product.getId(), product.getName(), product.getMeasurementName(),
                product.getMeasurementUnit());

    }

}
