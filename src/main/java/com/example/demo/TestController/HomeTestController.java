package com.example.demo.TestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeTestController {
    @GetMapping("/test")
    public String homeTest(){
        return "home";
    }
}
