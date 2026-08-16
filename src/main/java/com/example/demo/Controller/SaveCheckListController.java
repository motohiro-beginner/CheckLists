package com.example.demo.Controller;

import com.example.demo.DTO.SaveCheckListDTO;
import com.example.demo.Service.SaveCheckListService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveCheckListController {
    private final SaveCheckListService service;
    public SaveCheckListController(SaveCheckListService service){
        this.service = service;
    }
    /*saveCheckListはfetch通信で変更されたデータを受け取り、DBに保存する。*/
    @PostMapping("/saveCheckListName")
    public ResponseEntity<?> saveCheckList(
            @Valid @RequestBody SaveCheckListDTO dto,
            HttpSession session
    ){
        try {
            //セッション情報がなかったら、ログイン画面に戻る。
            if((session != null) && (session.getAttribute("userName") != null)) {
                service.updateColumns(dto);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ユーザー名が消失したため、ログイン画面に戻りました。");
            }
        }catch(RuntimeException e){
            //Serviceクラスから例外が投げられたら、badRequestを返す。
            return ResponseEntity.badRequest().body("更新処理に失敗しました。");
        }
    }
}
