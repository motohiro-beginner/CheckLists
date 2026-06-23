package com.example.demo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @PostMapping("/home")
    public ResponseEntity<?> home(HttpSession session){

    }
}
