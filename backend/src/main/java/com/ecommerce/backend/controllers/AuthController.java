package com.ecommerce.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.LoginRequest;
import com.ecommerce.backend.dto.LoginResponse;
import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.repositories.WebUserRepository;
import com.ecommerce.backend.security.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final WebUserRepository webUserRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            WebUserRepository webUserRepo,
            JwtService jwtService,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.webUserRepo = webUserRepo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    // -------------------------------
    // LOGIN
    // -------------------------------
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        WebUser user = webUserRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    // -------------------------------
    // REGISTER (optional)
    // -------------------------------
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody WebUser user) {

        if (webUserRepo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build(); 
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        WebUser saved = webUserRepo.save(user);

        String token = jwtService.generateToken(saved);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
