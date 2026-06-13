package com.example.demo.Controller;

import com.example.demo.DTO.NewRegistrationDTO;

import com.example.demo.Service.NewRegistrationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import java.util.Map;

@RestController
public class NewRegistrationController {
    @PostMapping("/newRegistration")
    public Map<String,Boolean> newRegistration(
            @Valid @RequestBody NewRegistrationDTO dto
    ){
        NewRegistrationService service = new NewRegistrationService();
        return Map.of("success",service.existsByName(dto.getUserName()));
    }
}
