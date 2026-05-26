package com.microservice.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;

    @Column(unique=true,
            nullable=false,
            length=120)
    private String email;

    @JsonIgnore
    @Column(name="password_hash",
            nullable=false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    @Column(nullable=false)
    private String status="ACTIVE";

    private Boolean emailVerified=false;

    @Column(
            name="created_at",
            updatable=false
    )
    private LocalDateTime createdAt=
            LocalDateTime.now();

    @Column(name="updated_at")
    private LocalDateTime updatedAt=
            LocalDateTime.now();

}