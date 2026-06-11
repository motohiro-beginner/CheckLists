package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class NewRegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "user_name",nullable = false,unique = false,length = 50)
    private String userName;
    @Column(name = "password",nullable = false,unique = true,length = 255)
    private String password;
    public NewRegistrationEntity(){}
    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }
}
