package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewRegistrationDTO {
    /*半角英文字または英数字のみで構成されていることをチェック*/
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]+$")
    private String userName;
    /*半角英文字または英数字で構成されていることをチェックするかつ8文字以上20字以下であることをチェック*/
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}+$")
    private String password;
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
