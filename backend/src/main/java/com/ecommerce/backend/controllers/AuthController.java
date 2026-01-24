package com.ecommerce.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.request.LoginRequest;
import com.ecommerce.backend.dto.request.ResetPasswordRequest;
import com.ecommerce.backend.dto.response.LoginResponse;
import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.services.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return this.authService.tryLogin(request);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody WebUser user) {
        return this.authService.register(user);
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest request) {
        return this.authService.resetPassword(request);
    }

}
