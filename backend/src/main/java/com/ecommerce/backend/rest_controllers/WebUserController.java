package com.ecommerce.backend.rest_controllers;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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
}
