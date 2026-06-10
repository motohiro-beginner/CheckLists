package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class NewRegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "userName",
            nullable = false,
    )
}
