package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

}
