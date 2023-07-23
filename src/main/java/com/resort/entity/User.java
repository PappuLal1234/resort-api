package com.resort.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    private UserType userType;

    public enum UserType {
        NORMAL_USER,
        VIEWER,
        ROLE_ADMIN
    }

    public User() {
        // Default constructor
    }

    public User(String username, String password, String email, UserType userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    // Getters, Setters, Constructors, etc.

    // Custom validation for admin email format
    @PrePersist
    @PreUpdate
    private void validateAdminEmail() {
        if (userType == UserType.ROLE_ADMIN && !email.toLowerCase().endsWith("@admin.com")) {
            throw new IllegalArgumentException("Admin emails must end with @admin.com.");
        }
    }
}