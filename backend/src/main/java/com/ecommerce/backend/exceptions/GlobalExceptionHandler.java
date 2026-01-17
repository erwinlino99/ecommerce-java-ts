package com.ecommerce.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.backend.controllers.AuthController;
import com.ecommerce.backend.dto.response.ErrorResponse;
import com.ecommerce.backend.dto.response.ImportResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final AuthController authController;

    public GlobalExceptionHandler(AuthController authController) {
        this.authController = authController;
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStock(OutOfStockException ex) {
        // PRIMERO CREAMOS EL ErrorResponse
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), "PRODUCTOS FUERA DE STROCK",
                ex.getErrors());
        // DEVOLVEMEOS DIRECTAMENTE JUTNO A ErrorResponse
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ImportException.class)
    public ResponseEntity<ImportResponse> handleImportErros(ImportException ex) {
        // CREAMOS EL CUERPO DEL IMPORT RESPONSE
        ImportResponse response = new ImportResponse(
                ex.getMessage(),
                ex.getSuccess(),
                ex.getErrors());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}