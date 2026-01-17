package com.ecommerce.backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ShopProductImportRequest(@NotBlank(message = "El nombre del producto no puede estar vacio") String name,
        @NotBlank(message = "Debe contenter una descripcion") String description,
        String shortDescription, @Positive(message = "El valor debe ser positivo") Double price,
        @Min(value = 0, message = "Valores mayores a 0") Integer currentStock,
        @NotBlank(message = "Debe contener nombre de marca") String brandName,
        @NotBlank(message = "Debe contener nombre de medida") String measurementName) {

}
