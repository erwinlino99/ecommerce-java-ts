package com.ecommerce.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shop_index")
public class ShopIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 255)
    private String path;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.modified = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}