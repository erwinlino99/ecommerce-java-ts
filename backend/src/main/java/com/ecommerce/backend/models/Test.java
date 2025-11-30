package com.ecommerce.backend.models;

import java.time.LocalDateTime;

public class Test extends BaseModel  {

    private Integer id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime deleted;
    private Boolean isActive;

    public Test() {
        super("shop_product_brand","spb");
    }

    public Test(Integer id, String name, LocalDateTime created, LocalDateTime modified, LocalDateTime deleted, Boolean isActive) {
        super("shop_product_brand","spb");
        this.id = id;
        this.name = name;
        this.created = created;
        this.modified = modified;
        this.deleted = deleted;
        this.isActive = isActive;
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
}
