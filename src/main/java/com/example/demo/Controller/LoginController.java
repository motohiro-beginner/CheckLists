package com.example.demo.Controller;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.Service.LoginService;
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

import java.util.Comparator;
import java.util.List;

@RestController
public class LoginController {
    private final LoginService service;
    public LoginController(LoginService service){
        this.service = service;
    }
    /*LoginControllerではユーザー名とパスワードを受け取りDB内に
    そのユーザー名とパスワードがあった場合ホーム画面に遷移する。*/
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginDTO dto,
            BindingResult result,
            HttpSession session
    ){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .sorted(Comparator.comparing(error ->
                                    switch(error.getField()) {
                        /*switch文でerrorに数値を割り振っている。そしてswitch文で割り振られた数値をもとに
                        sortedで並び替えを行う。*/
                        case "userName" -> 0;
                        case "password" -> 1;
                        default -> 99;
                                    }))
                    .map(FieldError::getDefaultMessage)
                    .toList();
            /*エラー文をjavascriptに返すためにFieldErrorからgetDefaultMessage型に変換する。*/
            return ResponseEntity.badRequest().body(errors);
        }
        boolean loginResult = service.existByUserNameAndPassword(dto.getUserName(),dto.getPassword());
        //ユーザー名及びパスワードがあっているかを判定する
        if(loginResult){
            session.setAttribute("userName",dto.getUserName());
            return ResponseEntity.ok("");
        }else{
            return ResponseEntity.badRequest().body("ユーザー名またはパスワードが違います。");
        }//ユーザー名及びパスワードがあっていた場合ok,違っていた場合badRequestを返す。
    }
}
