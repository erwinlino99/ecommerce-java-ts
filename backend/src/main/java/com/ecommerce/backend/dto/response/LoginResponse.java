package com.ecommerce.backend.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String token;
    private String roleName;
    private Integer webUserId;

    public LoginResponse(String token, Integer webUserId, String roleName) {
        this.token = token;
        this.webUserId = webUserId;
        this.roleName = roleName;
    }
}
