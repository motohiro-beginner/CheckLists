package com.example.demo.Controller;

import com.example.demo.DTO.NewRegistrationDTO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
public class NewRegistrationController {
    @PostMapping("/newRegistration")
    public String newRegistration(
            @Valid @RequestBody NewRegistrationDTO dto
    ){
        String userName = (String)data.get("userName");
        String password = (String)data.get("password");
    }
}
