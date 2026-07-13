package com.example.demo.Controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogOutController {
    @PostMapping("/logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "Login";
    }
}
