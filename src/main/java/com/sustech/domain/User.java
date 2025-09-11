package com.sustech.domain;

import com.sustech.utils.SecurityUtils;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Username cannot be null")  // 用于应用层校验
    @Column(name = "username", nullable = false)
    private String username;
    @NotNull(message = "Username cannot be null")  // 用于应用层校验
    @Column(name = "password_hash", nullable = false)
    private String password_hash;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "source_id")
    private int source_id;
    @Column(name = "permission_id")
    private int permission_id;
    @Column(name = "created_at")
    private String created_at;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password) {
        password_hash = SecurityUtils.encode(password);
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
