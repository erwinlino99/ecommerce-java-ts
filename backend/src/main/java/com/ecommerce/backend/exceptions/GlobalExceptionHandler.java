package com.ecommerce.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.backend.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStock(OutOfStockException ex) {
        // PRIMERO CREAMOS EL ErrorResponse
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), "PRODUCTOS FUERA DE STROCK",
                ex.getErrors());
        // DEVOLVEMEOS DIRECTAMENTE JUTNO A ErrorResponse
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}