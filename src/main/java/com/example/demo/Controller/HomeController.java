package com.example.demo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @PostMapping("/home")
    public RequestEntity<?> home(HttpSession session){

    }
}
