package com.ecommerce.backend.dto.request;

public record ShopProductRequest(
        Integer id,
        String name,
        String shortDescription,
        String description,
        Integer shopProductBrandId,
        String shopProductMeasurement,
        Integer currentStock,
        double price,
        String deleted) {

}