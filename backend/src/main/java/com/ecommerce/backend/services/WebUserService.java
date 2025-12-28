package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.repositories.WebUserRepository;

@Service
public class WebUserService {

    private final WebUserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public WebUserService(WebUserRepository repo) {
        this.repo = repo;
    }

    public List<WebUser> getAll() {
        return repo.findAll();
    }

    public WebUser register(WebUser newUser) {
        String encoded = encoder.encode(newUser.getPassword());
        newUser.setPassword(encoded);
        return repo.save(newUser);
    }

    public WebUser login(String email, String rawPassword) {
        WebUser user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    public WebUser findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public WebUser findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Integer getWebUserId(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No session found");
        }
        String email = auth.getName();
        return repo.findByEmail(email).map(WebUser::getId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

}
