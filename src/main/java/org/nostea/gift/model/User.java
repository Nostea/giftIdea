package org.nostea.gift.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private UserRole role;
    private List<Group> memberships;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public User(long id, String username, String password, String email, String avatar){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.role = UserRole.USER;
        this.memberships = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public User() {}

    // Getter & Setter
    public List<Group> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Group> memberships) {
        this.memberships = memberships;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
