package com.ecommerce.backend.dto.request;

import java.time.LocalDateTime;

public record WebUserRequest(
    Integer id,
    String name,
    String lastName,
    String email,
    String password,
    String cif,
    LocalDateTime created,
    LocalDateTime modified,
    LocalDateTime deleted,
    boolean isActive,
    boolean isBlocked,
    LocalDateTime last_time_login,
    String data
) {}