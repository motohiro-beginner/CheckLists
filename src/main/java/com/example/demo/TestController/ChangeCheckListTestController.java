package com.example.demo.TestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangeCheckListTestController {
    @GetMapping("/test")
    public String oneCheckListTest(){
        return "oneCheckList";
    }
}
