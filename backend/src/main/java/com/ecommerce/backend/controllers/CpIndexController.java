package com.ecommerce.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.CpIndex;
import com.ecommerce.backend.repositories.CpIndexRepository;

@RestController
@RequestMapping("/cp-index")
@CrossOrigin
public class CpIndexController {
    private CpIndexRepository cpRepo;

    public CpIndexController(CpIndexRepository cpRepo) {
        this.cpRepo = cpRepo;
    }

    @GetMapping
    public List<CpIndex> getAll() {
        return this.cpRepo.findAll();
    }
}
