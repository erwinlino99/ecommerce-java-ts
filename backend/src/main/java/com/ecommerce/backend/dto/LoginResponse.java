package com.ecommerce.backend.dto;

public class LoginResponse {
    private String token;
    private Integer webUserId;

    public LoginResponse(String token,Integer webUserId) {
        this.token = token;
        this.webUserId=webUserId;
    }

    public String getToken() { return token; }
    public Integer getWebUserId() { return webUserId; }
}
