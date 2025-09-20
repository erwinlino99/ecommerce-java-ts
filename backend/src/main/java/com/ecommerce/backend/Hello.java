package com.ecommerce.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/")
    public String ok() {
        return "Server is up and running Erwin!";
    }
}
