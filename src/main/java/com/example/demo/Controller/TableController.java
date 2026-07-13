package com.example.demo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {
    private final TableService service;
    public TableController(TableService service){
        this.service = service;
    }
    @PostMapping("/Table")
    public ResponseEntity<?> table(HttpSession session){
        if((session != null)&&(session.getAttribute("userName") != null)){

        }
    }
}
