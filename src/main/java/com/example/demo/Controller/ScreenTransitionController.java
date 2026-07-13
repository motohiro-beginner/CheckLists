package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScreenTransitionController {
    @GetMapping("/homeTransition")
    public String homeTransition(){
        return "home";
    }
    @GetMapping("/newRegistrationTransition")
    public String newRegistrationTransition(){
        return "newRegistration";
    }
    @GetMapping("/screenTest")
    public String screenTest(){
        return "test";
    }
}
