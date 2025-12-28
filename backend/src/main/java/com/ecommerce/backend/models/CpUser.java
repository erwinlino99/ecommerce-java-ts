package com.ecommerce.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cp_user")
public class CpUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(name = "surname", length = 150)
    private String surName;

    @Column
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "cp_role_id")
    private CpRole role;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @Column
    private LocalDateTime deleted;

    @Column(nullable = false)
    private Boolean isActive;

    public CpUser() {

    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.modified = now;

        if (this.isActive == null) {
            this.isActive = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }

}
