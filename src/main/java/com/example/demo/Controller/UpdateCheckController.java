package com.example.demo.Controller;

import com.example.demo.DTO.CheckDTO;
import com.example.demo.Service.UpdateCheckService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateCheckController {
    private final UpdateCheckService service;
    public UpdateCheckController(UpdateCheckService service){
        this.service = service;
    }
    /*updateCheckはユーザーがつけたチェックの情報を受け取り、ServiceクラスのsaveIsCheckedを呼び出すためのメソッドである。
    * 具体的にはユーザーがとある項目にチェックを付けたり外したりしたら、その情報をfetch通信で受け取り、saveIsCheckedを呼び出す。*/
    @PostMapping("/updateCheck")
    public ResponseEntity<?> updateCheck(
            @Valid @RequestBody CheckDTO dto,
            HttpSession session
    ){
        try{
            if(session != null&&session.getAttribute("userName") != null) {
                service.saveIsChecked((String) session.getAttribute("userName"), dto);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ユーザー情報が消失したためログイン画面に戻りました。");
            }
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(RuntimeException e){
            System.out.println("コードに誤りがあります。");
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            System.out.println("Exception発生");
            return ResponseEntity.badRequest().body("原因不明のエラーが発生したため、更新処理が失敗しました。");
        }
    }
}
