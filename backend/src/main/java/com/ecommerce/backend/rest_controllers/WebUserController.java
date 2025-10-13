package com.ecommerce.backend.rest_controllers;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebUserController {

    private final JdbcTemplate jdbc;

    public WebUserController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/web_user")
    public List<Map<String, Object>> getAllWebUsers() {
        return jdbc.queryForList("SELECT * FROM `web_user`");
    }

    @PostMapping("/web_user")
    public boolean tryToRegister(@RequestBody Map<String, Object> newUser) {
        System.out.println("------");
        System.out.println("------");
        System.out.println(" INFO-> : " + newUser);
        try {
            //NEED TO ENCRYP INFORMATION FOR SECURUTY
            String password = String.valueOf(newUser.get("password"));
            BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
            String encodePassWord=encoder.encode(password);
            String sql = """
            INSERT INTO web_user (name, last_name, email, password, created)
            VALUES (?, ?, ?, ?, NOW()) """;
            jdbc.update(sql,
                    newUser.get("firstName"),
                    newUser.get("lastName"),
                    newUser.get("email"),
                    encodePassWord
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
