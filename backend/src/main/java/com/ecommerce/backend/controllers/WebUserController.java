package com.ecommerce.backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.services.WebUserService;

@RestController
@CrossOrigin
public class WebUserController {

    private final WebUserService webUserService;

    public WebUserController(WebUserService webUserService) {
        this.webUserService = webUserService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebUser> getById(@PathVariable Integer id) {
        WebUser user = webUserService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<WebUser> getAll() {
        return webUserService.getAll();
    }

    @PostMapping("/web_user")
    public WebUser register(@RequestBody WebUser newUser) {
        return webUserService.register(newUser);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        WebUser user = webUserService.login(email, password);
        return Map.of(
                "status", 200,
                "id", user.getId(),
                "email", user.getEmail()
        );
    }
}
