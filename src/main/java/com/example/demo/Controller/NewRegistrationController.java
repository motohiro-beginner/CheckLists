package com.example.demo.Controller;

import com.example.demo.DTO.NewRegistrationDTO;

import com.example.demo.Service.NewRegistrationService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
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
import java.util.Map;

@RestController
public class NewRegistrationController {
    private final NewRegistrationService service;
    public NewRegistrationController(NewRegistrationService service){
        this.service = service;
    }
    @PostMapping("/newRegistration")
    public ResponseEntity<?> newRegistration(
            @Valid @RequestBody NewRegistrationDTO dto,
            BindingResult result,
            HttpSession session
    ){
        try {
            boolean registrationResult = service.existsByUserName(dto.getUserName());
            if (result.hasErrors()) {
                List<String> errors = result.getFieldErrors()
                        .stream()
                        .sorted(Comparator.comparing(error ->
                            switch(error.getField()){
                                case "userName" -> 0;
                                case "password" -> 1;
                                default -> 99;
                            }))
                        .map(FieldError::getDefaultMessage)
                        .toList();
            /*ユーザーIDとパスワードの内容をチェックして問題があった場合、
            エラーの内容を返すためにgetDefaultMessageに返す。*/
                return ResponseEntity.badRequest().body(errors);
                /*入力失敗であるという結果を返す。*/
            }
            if (registrationResult) {
                return ResponseEntity.badRequest().body("ユーザー名は既に他の人に使われています。");
            } else {
                service.save(dto);
                //新しいユーザー名およびパスワードを挿入
                session.setAttribute("loginUserName", dto.getUserName());
                //セッション管理
                return ResponseEntity.ok("");
            }/*同じuserNameが既に登録されている場合はfalseをjavaScriptに返し
             他にないuserNameの場合はDBに挿入し、trueを返す。*/
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
