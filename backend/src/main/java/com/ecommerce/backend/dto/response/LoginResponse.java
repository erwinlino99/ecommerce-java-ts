package com.ecommerce.backend.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String token;
    private final String roleName;
    private final Integer webUserId;

    public LoginResponse(String token, Integer webUserId, String roleName) {
        this.token = token;
        this.webUserId = webUserId;
        this.roleName = roleName;
    }
}
