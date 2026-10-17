package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class OneCheckListUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "user_name",length = 50,unique = true)
    private String userName;
    @Column(name = "password",length = 255)
    private String password;
    @OneToMany(mappedBy = "user")
    private List<OneCheckListCheckListsEntity> checkList;
    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }
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
    public List<OneCheckListCheckListsEntity> getCheckList(){
        return List.copyOf(checkList);
    }
    public void setCheckList(List<OneCheckListCheckListsEntity> checkList){
        this.checkList = List.copyOf(checkList);
    }
}
