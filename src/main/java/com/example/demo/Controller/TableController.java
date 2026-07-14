package com.example.demo.Controller;

import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Service.TableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TableController {
    private final TableService service;
    public TableController(TableService service){
        this.service = service;
    }
    @PostMapping("/Table")
    public ResponseEntity<?> table(HttpSession session){
        if((session != null)&&(session.getAttribute("userName") != null)){
            //セッションの情報があるかないかを確かめる。もし消失していたら、badRequestを返す。
            if(service.existsByUserCheckLists((String)session.getAttribute("userName"))){
                //特定のユーザーのチェックリストがあるかないかを確かめる。もしないならnotFoundを返す。
                List<TableViewDTO> view = service.findTableAllCheckLists((String)session.getAttribute("userName"));
                //チェックリストの情報を返す。
                return ResponseEntity.ok(view);
            }else{
                return ResponseEntity.notFound().build();
            }
        }
    }
}
