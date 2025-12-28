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

@Entity
@Table(name = "web_user")
public class WebUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(name = "last_name", length = 150)
    private String lastName;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(length = 50)
    private String cif;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @Column
    private LocalDateTime deleted;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isBlocked;

    @Column(name = "last_time_login")
    private LocalDateTime lastTimeLogin;

    @Column(columnDefinition = "TEXT")
    private String data;


    public WebUser() {

    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.modified = now;

        if (this.isActive == null) {
            this.isActive = true;
        }
        if (this.isBlocked == null) {
            this.isBlocked = false;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public LocalDateTime getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(LocalDateTime lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    

}
