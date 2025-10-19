package com.ecommerce.backend.rest_controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.backend.models.WebUser;

@RestController
public class WebUserController {

    private static final Logger log = LoggerFactory.getLogger(WebUserController.class);
    private final JdbcTemplate jdbc;

    public WebUserController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/web_user")
    public List<Map<String, Object>> getAllWebUsers() {
        WebUser wb = new WebUser();
        return wb.getAll();
    }

    @PostMapping("/web_user")
    public boolean tryToRegister(@RequestBody Map<String, Object> newUser) {
        log.info("WEB USER : {} ", newUser);
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String passwordEncoded=encoder.encode(String.valueOf(newUser.get("password")));
        newUser.put("password", passwordEncoded);
        WebUser wb = new WebUser();
        return wb.createNewRecord(newUser);
    }

    @PostMapping("/login")
    public Map<String, Object> tryToLogin(@RequestBody Map<String, Object> loginWebUser) {
        log.info("LOGIN WEB USER INFO> {}", loginWebUser);
        String email = String.valueOf(loginWebUser.get("email"));
        String password = String.valueOf(loginWebUser.get("password"));
        try {
            String sql = "SELECT id, password FROM web_user WHERE email = ?";
            Map<String, Object> user = jdbc.queryForMap(sql, email);
            String hashedPassword = String.valueOf(user.get("password"));
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, hashedPassword)) {
                log.info(" LOGIN CORRECTO PARA {}", email);
                //web_user_id = id
                return Map.of(
                        "status", 200,
                        "id", user.get("id")
                );
            } else {
                log.warn(" Contraseña incorrecta para {}", email);
                return Map.of("status", "invalid_password");
            }
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            log.warn(" Usuario no encontrado: {}", email);
            return Map.of("status", "user_not_found");
        } catch (Exception e) {
            log.error(" Error en login: {}", e.getMessage());
            return Map.of("status", "error");
        }
    }
}
