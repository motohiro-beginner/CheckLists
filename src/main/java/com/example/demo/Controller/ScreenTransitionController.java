package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScreenTransitionController {
    @GetMapping("/tableTransition")
    public String tableTransition(){
        return "table";
    }
}
