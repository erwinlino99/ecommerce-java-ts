package com.ecommerce.backend.exceptions;

import java.util.List;

import com.ecommerce.backend.dto.response.OutOfStockProductResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutOfStockException extends RuntimeException {
    private List<OutOfStockProductResponse> errors;

    public OutOfStockException(List<OutOfStockProductResponse> errors) {
        super("STOCK INSUFICIENTE PARA LOS PRODUCTOS SELECCIONADOS");
        this.errors = errors;
    }
}
