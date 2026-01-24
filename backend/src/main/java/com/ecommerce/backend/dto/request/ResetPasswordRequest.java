package com.ecommerce.backend.dto.request;

import lombok.Getter;

@Getter
public class ResetPasswordRequest {

    private String email;
    private String newPassword;

}
