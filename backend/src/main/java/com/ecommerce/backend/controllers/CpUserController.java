package com.ecommerce.backend.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.models.CpUser;
import com.ecommerce.backend.repositories.CpUserRepository;

@RestController
@CrossOrigin
public class CpUserController {
    private final CpUserRepository cpUserRepo;

    public CpUserController(CpUserRepository cpUserRepo) {
        this.cpUserRepo = cpUserRepo;
    }

    @GetMapping("/cp-user")
    public ResponseEntity<Optional<CpUser>> getCpUserByToken(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        String email = auth.getName();
        Optional<CpUser> user = this.cpUserRepo.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}
