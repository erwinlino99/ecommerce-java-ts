package com.ecommerce.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.NewWebUser;
import com.ecommerce.backend.repositories.NewWebUserRepository;

@RestController
@RequestMapping("/web_user")
public class NewWebUserController {
    private final NewWebUserRepository repository;
    public NewWebUserController(NewWebUserRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<NewWebUser> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
