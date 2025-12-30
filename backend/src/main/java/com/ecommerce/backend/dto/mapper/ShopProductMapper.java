package com.ecommerce.backend.dto.mapper;

import com.ecommerce.backend.dto.ShopProductDto;
import com.ecommerce.backend.dto.ShopProductLiteDto;
import com.ecommerce.backend.models.ShopProduct;

public class ShopProductMapper {

    public static ShopProductDto toFullDto(ShopProduct product) {
        if (product == null) {
            return null;
        }
        return new ShopProductDto(
                product.getId(),
                product.getName(),
                product.getBrandName(),
                product.getDescription(),
                product.getShortDescription(),
                product.getPrice(),
                product.getCurrentStock(),
                product.getMeasurementName(),
                product.getMeasurementUnit(),
                product.getCreated(),
                product.getModified(),
                product.getDeleted());
    }

    public static ShopProductLiteDto toDto(ShopProduct product) {
        if (product == null) {
            return null;
        }
        return new ShopProductLiteDto(product.getId(), product.getName(), product.getMeasurementName(),
                product.getMeasurementUnit());

    }

}
