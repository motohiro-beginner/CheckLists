package com.example.demo.Controller;

import com.example.demo.DTO.HomeCheckListsViewDTO;
import com.example.demo.Service.HomeCheckListsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import java.util.List;

@RestController
public class HomeController {
    private final HomeCheckListsService service;
    public HomeController(HomeCheckListsService service){
        this.service = service;
    }
    @PostMapping("/home")
    public ResponseEntity<?> home(HttpSession session){
        if((session != null)&&(session.getAttribute("userName") != null)){
            List<HomeCheckListsViewDTO> resultCheckLists = service.findAllCheckLists((String)session.getAttribute("userName"));
            return ResponseEntity.ok(resultCheckLists);
        }
        return ResponseEntity.badRequest().body("ユーザー名が消失したため、Login画面に戻りました。");
    }
}
