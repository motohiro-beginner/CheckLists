package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransitionController {
    @GetMapping("/changeCheckList/{id}")
    public String changeCheckList(){
        return "changeCheckList";
    }
    @GetMapping("/addCheckList")
    public String addCheckList(){
        return "addCheckList";
    }
}
