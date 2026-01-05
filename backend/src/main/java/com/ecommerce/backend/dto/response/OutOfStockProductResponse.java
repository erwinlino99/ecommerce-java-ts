package com.ecommerce.backend.dto.response;

public record OutOfStockProductResponse(String shopProductName, Integer requestedQuantity, Integer availableStock) {

}
