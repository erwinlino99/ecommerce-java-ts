package com.ecommerce.backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ShopProductImportRequest(@NotBlank String name, @NotBlank String description,
                @NotBlank String shortDescription, @Positive Double price,
                @Min(0) Integer currentStock, @NotBlank String brandName, @NotBlank String measurementName) {

}
