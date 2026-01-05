package com.ecommerce.backend.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.request.LoginRequest;
import com.ecommerce.backend.dto.response.LoginResponse;
import com.ecommerce.backend.models.CpUser;
import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.repositories.CpUserRepository;
import com.ecommerce.backend.repositories.WebUserRepository;
import com.ecommerce.backend.security.JwtService;
import com.ecommerce.backend.util.UseLogger;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final WebUserRepository webUserRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CpUserRepository cpUserRepo;

    public AuthController(
            AuthenticationManager authenticationManager,
            WebUserRepository webUserRepo,
            JwtService jwtService,
            PasswordEncoder passwordEncoder, CpUserRepository cpUserRepo) {
        this.authenticationManager = authenticationManager;
        this.webUserRepo = webUserRepo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.cpUserRepo = cpUserRepo;
    }

    // -------------------------------
    // LOGIN
    // -------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // UseLogger.info("--- INICIO PROCESO LOGIN ---", "");
        // UseLogger.info("Email recibido: ", request.getEmail());
        // UseLogger.info("Password recibida (longitud): ", String.valueOf(request.getPassword().length()));

        try {
            // --- BLOQUE DE DEBUG DE EMERGENCIA ---
            Optional<CpUser> debugUser = cpUserRepo.findByEmail(request.getEmail());
            if (debugUser.isPresent()) {
                String hashDB = debugUser.get().getPassword();
                boolean matches = passwordEncoder.matches(request.getPassword(), hashDB);

                // UseLogger.info("¿COINCIDENCIA MANUAL BCrypt?: ", matches ? "SÍ (TRUE)" : "NO (FALSE)");
                // UseLogger.info("Hash actual en DB: ", hashDB);

                // Generamos un hash fresco para que lo compares
                String nuevoHashTest = passwordEncoder.encode(request.getPassword());
                // UseLogger.info("Si generamos un hash ahora para '" + request.getPassword() + "' sería: ",
                //         nuevoHashTest);
            }
            // ---------------------------------------

            // Intentar autenticación oficial de Spring
            UseLogger.info("Llamando a authenticationManager.authenticate...", "");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            // Si llegamos aquí, Spring ha dado el OK
            UseLogger.info("¡Autenticación exitosa en Spring Security!", "");

            // 1. Caso Admin
            Optional<CpUser> cpUser = cpUserRepo.findByEmail(request.getEmail());
            if (cpUser.isPresent()) {
                CpUser admin = cpUser.get();
                String token = jwtService.generateToken(admin);
                String role = (admin.getRole() != null) ? admin.getRole().getName() : "ROLE_ADMIN";

                UseLogger.info("Login Finalizado como ADMIN para: ", admin.getEmail());
                return ResponseEntity.ok(new LoginResponse(token, admin.getId(), role));
            }

            // 2. Caso WebUser
            Optional<WebUser> webUser = webUserRepo.findByEmail(request.getEmail());
            if (webUser.isPresent()) {
                WebUser client = webUser.get();
                String token = jwtService.generateToken(client);

                UseLogger.info("Login Finalizado como CLIENTE para: ", client.getEmail());
                return ResponseEntity.ok(new LoginResponse(token, client.getId(), "ROLE_CLIENT"));
            }

            return ResponseEntity.status(401).body("{\"error\": \"Usuario no encontrado en tablas\"}");

        } catch (BadCredentialsException e) {
            UseLogger.warning("ERROR: Credenciales inválidas para: ", request.getEmail());
            return ResponseEntity.status(401).body("{\"error\": \"Email o contraseña incorrectos\"}");
        } catch (Exception e) {
            UseLogger.error("ERROR CRÍTICO: ", e.getMessage());
            e.printStackTrace(); // Para ver el error completo en consola
            e.printStackTrace(); // Para ver el error completo en consola
            return ResponseEntity.status(500).body("{\"error\": \"Fallo interno: " + e.getMessage() + "\"}");
        }
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
        return ResponseEntity.ok(new LoginResponse(token, user.getId(), ""));
    }
}
