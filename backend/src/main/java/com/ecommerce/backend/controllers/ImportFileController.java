package com.ecommerce.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.services.ImportFileService;

@RestController
@RequestMapping("/file")
public class ImportFileController {

    private ImportFileService service;

    public ImportFileController(ImportFileService service) {
        this.service = service;
    }

    @GetMapping("/download-template")
    @SuppressWarnings("UseSpecificCatch")
    public ResponseEntity getXlsxTemplate(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return this.service.getXlsxTemplate();
    }

    @PostMapping("/upload-template")
    public ResponseEntity<?> readXlsxTemplate(@RequestParam("file") MultipartFile file, Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return this.service.readXlsxTemplate(file);
    }

}
