package com.ecommerce.backend.dto;

import java.time.LocalDateTime;

public record WebUserDto(
    Integer id,
    String name,
    String lastName,
    String email,
    String cif,
    String password, // Opcional: eliminar si vas a enviar datos al Front-End
    LocalDateTime created,
    LocalDateTime modified,
    LocalDateTime deleted,
    Boolean isActive,
    Boolean isBlocked,
    LocalDateTime lastTimeLogin
) {
}