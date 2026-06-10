package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
public class NewRegistrationController {
    @PostMapping("/newRegistration")
    public String newRegistration(
            @RequestBody Map<String,Object> data
    ){
        String userName = (String)data.get("userName");
        String password = (String)data.get("password");
    }
}
