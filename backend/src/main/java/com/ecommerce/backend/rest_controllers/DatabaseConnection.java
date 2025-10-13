package com.ecommerce.backend.rest_controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {

    private final JdbcTemplate jdbc;

    public DatabaseConnection(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate execute() {
        return jdbc;
    }
}
