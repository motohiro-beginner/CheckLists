package com.example.demo.Entity;

import jakarta.persistence.*;
/*users表は
 * user_id
 * user_name
 * passwordの３つの列がある。*/
@Entity
@Table(name = "users")
public class HomeUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "user_name",nullable = false,unique = true,length = 50)
    private String userName;
    @Column(name = "password",nullable = false,unique = true,length = 255)
    private String password;
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
