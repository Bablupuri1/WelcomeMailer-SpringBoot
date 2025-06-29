package com.ecom.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    
    private String password;


    private String mobile;
    private String address;
    private String role; // e.g., ADMIN, USER, etc.
    
    
    private boolean active = true;
    
    @CurrentTimestamp
    private LocalDateTime createdAt;
}
