package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity//Entityクラスであることを明示
@Table(name = "users")//usersテーブルの行が取り出されることを表す。
public class LoginEntity {
    @Id//下の変数が主キーであることを表す。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "user_name",nullable = false,unique = false,length = 50)
    private String userName;
    //ユーザー名である。null禁止,重複あり,文字数は50文字まで
    @Column(name = "password",nullable = false,unique = true,length = 255)
    private String password;
    //パスワードである。null禁止,重複あり,文字数は255文字まで
    public LoginEntity(){}
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
