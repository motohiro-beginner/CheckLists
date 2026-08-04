package com.example.demo.Controller;

import com.example.demo.DTO.OneCheckListViewDTO;
import com.example.demo.Service.OneCheckListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneCheckListController {
    private final OneCheckListService service;
    public OneCheckListController(OneCheckListService service){
        this.service = service;
    }
    //showOneCheckListはurlでチェックリストidを受け取り、そのチェックリストを返すメソッドである。
    @PostMapping("/showOneCheckList/{checkListsId}")
    public ResponseEntity<?> showOneCheckList(
            @PathVariable Integer checkListsId,
            HttpSession session
    ){
        //セッション情報が消失していた場合、badRequestを送り、ログイン画面に戻る。
        if(session != null && session.getAttribute("userName") != null) {
            OneCheckListViewDTO view = service.findOneCheckList(checkListsId);
            return ResponseEntity.ok(view);
        }else{
            return ResponseEntity.badRequest().body("ユーザー名が消失したため、ログイン画面に戻りました。");
        }
    }
}
