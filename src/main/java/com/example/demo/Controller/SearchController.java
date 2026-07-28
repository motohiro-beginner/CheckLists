package com.example.demo.Controller;

import com.example.demo.DTO.TableSearchDTO;
import com.example.demo.Service.SearchService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    private final SearchService service;
    public SearchController(SearchService service){
        this.service = service;
    }
    @PostMapping("/search")
    public ResponseEntity<?> search(
            @Valid TableSearchDTO dto,
            HttpSession session
    ){
        if((session != null)&&(session.getAttribute("userName") != null)){
        }else{
            return ResponseEntity.badRequest().body("ユーザー名が消失したためログイン画面に戻りました。");
        }
    }
}
