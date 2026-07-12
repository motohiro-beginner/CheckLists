package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScreenTransitionController {
    @GetMapping("/homeTransition")
    public String homeTransition(){
        return "home";
    }
    @GetMapping("/loginTransition")
    public String loginTransition(){
        return "Login";
    }
}
