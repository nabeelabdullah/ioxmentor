package com.codingsuperstar.codingsuperstar.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by nabeelabdullah on 01/11/17.
 */
@Entity
public class Validation {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true)
    private String code;

    private Long userId;

    private Timestamp createdAt;

    private Timestamp expAt;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getExpAt() {
        return expAt;
    }

    public void setExpAt(Timestamp expAt) {
        this.expAt = expAt;
    }
}
