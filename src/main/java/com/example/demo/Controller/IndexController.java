package com.example.demo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
    @GetMapping("/")
    public String screenTransition(HttpSession session){
        Object userName = session.getAttribute("userName");
        if(userName != null){
            return "redirect:/home";
        }else{
            return "redirect:/newRegistration";
        }
    }
}
