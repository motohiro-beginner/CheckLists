package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewRegistrationDTO {
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]+$")/*半角英文字または英数字のみで構成されていることをチェック*/
    private String userName;
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$")
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
