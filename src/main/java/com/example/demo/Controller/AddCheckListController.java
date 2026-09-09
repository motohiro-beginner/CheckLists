package com.example.demo.Controller;

import com.example.demo.DTO.AddCheckListDTO;
import com.example.demo.Service.AddCheckListService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddCheckListController {
    private final AddCheckListService service;
    public AddCheckListController(AddCheckListService service){
        this.service = service;
    }
    //addCheckListは新規作成されたチェックリストの情報をDBに保存するためのメソッドである。
    @PostMapping("/addCheckList")
    public ResponseEntity<?> addCheckList(
            @Valid @RequestBody AddCheckListDTO dto,
            HttpSession session
    ){
        try {
            if (session != null && session.getAttribute("userName") != null) {
                service.addCheckList((String)session.getAttribute("userName"),dto);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ユーザー名が消失したため、ログイン画面に戻りました。");
            }
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
