package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class NewRegistrationEntity {
    //users表のEntityクラス,Entityクラスは一つのインスタンスにつき一つの行になる。
    //user_id列の値を表している。user_idは主キーである。
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    //ユーザー名である。null禁止,重複禁止,文字数は50文字まで
    @Column(name = "user_name",nullable = false,unique = false,length = 50)
    private String userName;
    //パスワードである。null禁止,重複あり,文字数255文字
    @Column(name = "password",nullable = false,unique = true,length = 255)
    private String password;
    public NewRegistrationEntity(){}
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
