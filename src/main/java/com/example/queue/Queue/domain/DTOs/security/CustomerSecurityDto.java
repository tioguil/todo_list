package com.example.queue.Queue.domain.DTOs.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerSecurityDto extends User {

    private final String id;
    private final String name;
    private final String email;
    private final List<GrantedAuthority> authorities;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CustomerSecurityDto(String id, String email, String password, String name, List<GrantedAuthority> authorities, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(email, password, authorities);
        this.id = id;
        this.email = email;
        this.name = name;
        this.authorities = authorities;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName()).append(" [");
        sb.append("id=").append(this.id).append(", ");
        sb.append("Name=").append(this.name).append(", ");
        sb.append("Username=").append(this.email).append(", ");
        sb.append("Password=[PROTECTED], ");
        sb.append("CreatedAt=").append(this.createdAt).append(", ");
        sb.append("UpdatedAd=").append(this.updatedAt).append(", ");
        sb.append("Granted Authorities=").append(this.authorities).append("]");
        return sb.toString();
    }

}