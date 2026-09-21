package com.example.demo.TestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*html画面がどのように表示されているかを確認するためにある。*/
@Controller
public class AddCheckListTestController {
    @GetMapping("/test")
    public String displayScreen(){
        return "addCheckList";
    }
}