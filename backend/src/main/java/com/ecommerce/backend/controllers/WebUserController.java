package com.ecommerce.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.services.WebUserService;

@RestController
@CrossOrigin
public class WebUserController {

    private final WebUserService service;

    public WebUserController(WebUserService service) {
        this.service = service;
    }

    @GetMapping("web-user/{id}")
    public ResponseEntity<WebUser> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
