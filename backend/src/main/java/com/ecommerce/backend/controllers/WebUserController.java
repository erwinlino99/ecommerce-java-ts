package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.dto.WebUserDto;
import com.ecommerce.backend.dto.mapper.WebUserMapper;
import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.services.WebUserService;

@RestController
@CrossOrigin
public class WebUserController {

    private final WebUserService service;

    public WebUserController(WebUserService service) {
        this.service = service;
    }

    @GetMapping("/web-user")
    public ResponseEntity<WebUser> getWebUserByToken(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        String email = auth.getName();
        WebUser user = service.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/summary-web-users")
    public List<WebUserDto> getSummaryWebUsers(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return WebUserMapper.toDtoList(this.service.getAll());
    }

}
