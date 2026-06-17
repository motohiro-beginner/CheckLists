package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {
    @NotBlank(message = "ユーザーIDを入力してください。")
    private String userName;
    @NotBlank(message = "パスワードを入力してください。")
    private String password;
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
