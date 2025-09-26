package com.ecommerce.backend.rest_controllers;

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
        return "JAVA ENVIRONMENT IS UP AND RUNNING";
    }
}
