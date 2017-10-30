package com.codingsuperstar.codingsuperstar.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp expAt;

    @Column(unique = true)
    private String token;

    @Column
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
