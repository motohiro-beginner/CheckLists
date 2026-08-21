package com.example.demo.Controller;

import com.example.demo.DTO.DeleteCheckListDTO;
import com.example.demo.Service.DeleteCheckListService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteCheckListController {
    DeleteCheckListService service;
    public DeleteCheckListController(DeleteCheckListService service){
        this.service = service;
    }
    //deleteCheckListはチェックリストidをfetch通信で受け取り、そのidに一致するチェックリストの情報を削除するメソッドである。
    @PostMapping("/deleteCheckList")
    public ResponseEntity<?> deleteCheckList(
            @Valid @RequestBody DeleteCheckListDTO dto,
            HttpSession session
    ){
        try {
            if (session != null && session.getAttribute("userName") != null) {
                service.deleteCheckList(dto.getCheckListsId());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ユーザー名が消失したためログイン画面に戻りました。");
            }
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
