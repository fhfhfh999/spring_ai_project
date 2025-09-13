package com.sustech.domain;

import com.sustech.utils.SecurityUtils;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 用于应用层校验
    @Column(name = "username", nullable = false)
    private String username; // 用于应用层校验
    @Column(name = "password_hash", nullable = false)
    private String password;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "source_id")
    private int source_id;
    @Column(name = "permission_id")
    private int permission_id;
    @Column(name = "created_at")
    private Timestamp created_at;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = SecurityUtils.encode(password);
        this.email = email;
        this.source_id = 1;
        this.permission_id = 1;
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回用户的角色/权限
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = SecurityUtils.encode(password);
        this.password = password;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
