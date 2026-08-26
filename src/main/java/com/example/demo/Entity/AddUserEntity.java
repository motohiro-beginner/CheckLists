package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AddUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "user_name",nullable = false,unique = true,length = 50)
    private String userName;
    @Column(name = "password",nullable = false,unique = false,length = 255)
    private String password;
    @OneToMany(mappedBy = "user")
    private List<AddCheckListEntity> checkList;
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassword()

}
