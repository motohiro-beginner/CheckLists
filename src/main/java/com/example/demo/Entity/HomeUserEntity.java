package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

/*users表は
  CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)*/

@Entity
@Table(name = "users")
public class HomeUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "user_name",nullable = false,unique = true,length = 50)
    private String userName;
    @Column(name = "password",nullable = false,unique = false,length = 255)
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
    @OneToMany(mappedBy = "user")
    private List<HomeCheckListsEntity> checkLists;
    public List<HomeCheckListsEntity> getCheckLists(){
        return checkLists;
    }
    public void setCheckLists(List<HomeCheckListsEntity> checkLists){
        this.checkLists = checkLists;
    }
}
