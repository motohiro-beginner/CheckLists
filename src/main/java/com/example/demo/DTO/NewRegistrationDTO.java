package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewRegistrationDTO {
    /*半角英文字または半角数字のみで構成されていることをチェック*/
    @NotBlank(message = "ユーザーIDを入力してください。")
    @Pattern(
            regexp = "^[A-Za-z\\d]+$",
            message = "ユーザーIDは半角英数字のみです。"
    )
    private String userName;
    /*半角英文字または英数字で構成されていることをチェックするかつ8文字以上20字以下であることをチェック*/
    @NotBlank(message = "パスワードを入力してください。")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}+$",
            message = "パスワードは8～20文字の半角英数字で、英字と数字をそれぞれ１文字以上含んでください。"
    )
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
