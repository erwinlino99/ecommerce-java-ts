package com.ecommerce.backend.dto.request;

public record ShopProductRequest(
        Integer id,
        String name,
        String shortDescription,
        String description,
        String brandName,
        String shopProductMeasurement,
        Integer currentStock,
        double price,
        String deleted) {

}