package com.example.demo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
public class LoginController {
    /*LoginControllerではユーザー名とパスワードを受け取りDB内に
    そのユーザー名とパスワードがあった場合ホーム画面に遷移する。*/
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody
    )
}
