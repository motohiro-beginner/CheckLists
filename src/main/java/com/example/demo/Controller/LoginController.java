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

import java.util.List;

@RestController
public class LoginController {
    /*LoginControllerではユーザー名とパスワードを受け取りDB内に
    そのユーザー名とパスワードがあった場合ホーム画面に遷移する。*/
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginDTO dto,
            HttpSession session,
            BindingResult result
    ){
        LoginService service = new LoginService();
        boolean loginResult = service.existByUserNameAndPassword(dto.getUserName(),dto.getPassword());
        //ユーザー名及びパスワードがあっているかを判定する
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            /*エラー文をjavascriptに返すためにFieldErrorからgetDefaultMessage型に変換する。*/
            return ResponseEntity.badRequest().body(errors);
        }
        if(loginResult){
            session.setAttribute("userName",dto.getUserName());
            return ResponseEntity.ok("");
        }else{
            return ResponseEntity.badRequest().body("ユーザー名またはパスワードが違います。");
        }//ユーザー名及びパスワードがあっていた場合ok,違っていた場合badRequestを返す。
    }
}
