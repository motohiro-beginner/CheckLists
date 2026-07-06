package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TestLoginController {
    //Webブラウザでhtml,cssファイルがどのように表示されるかを確かめるために作ったControllerである。
    @GetMapping("/test")
    public String testLogin(){
        return "login";
    }
}
