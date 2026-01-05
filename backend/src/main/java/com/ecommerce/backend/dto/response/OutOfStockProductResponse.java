package com.ecommerce.backend.dto.response;

public record OutOfStockProductResponse(Integer shopProductId,String shopProductName, Integer requestedQuantity) {

}
