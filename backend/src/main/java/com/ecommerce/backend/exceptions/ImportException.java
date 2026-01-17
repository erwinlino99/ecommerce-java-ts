package com.ecommerce.backend.exceptions;

import java.util.List;

import lombok.Getter;

@Getter
public class ImportException extends RuntimeException {
    private final List<String> errors;
    private final Integer success;

    public ImportException(List<String> errors, Integer success) {
        super("Errores al importar productos");
        this.errors = errors;
        this.success = success;
    }
}
