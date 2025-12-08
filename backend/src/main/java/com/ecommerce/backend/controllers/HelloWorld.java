package com.ecommerce.backend.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
  origins = {
    "http://localhost:4200"
  },
  allowCredentials = "true"
)
public class HelloWorld {
    @GetMapping("/")
  public String ok() {
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return "Ok.JAVA ENVIRONMENT IS UP AND RUNNING :<br> Current Time: " + formattedTime;
    }
}
