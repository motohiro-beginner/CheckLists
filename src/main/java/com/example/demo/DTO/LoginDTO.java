package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {
    @NotBlank(message = "ユーザーIDを入力してください。")
    private String userName;
    @NotBlank(message = "パスワードを入力してください。")
    private String password;
}
