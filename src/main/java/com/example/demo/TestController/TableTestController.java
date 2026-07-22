package com.example.demo.TestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableTestController {
    //tableTestはtable.htmlの画面がどのように表示されるかを確かめるために作ったController
    @GetMapping("/test")
    public String tableTest(){
        return "table";
    }
}
