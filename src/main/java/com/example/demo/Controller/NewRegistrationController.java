package com.example.demo.Controller;

import com.example.demo.DTO.NewRegistrationDTO;

import com.example.demo.Service.NewRegistrationService;

import jakarta.servlet.http.HttpSession;
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
            @Valid @RequestBody NewRegistrationDTO dto,
            HttpSession session
    ){
        NewRegistrationService service = new NewRegistrationService();
        boolean registrationResult = service.existsByUserName(dto.getUserName());
        if(registrationResult){
            return Map.of("registrationResult",registrationResult);
        }else {
            service.save(dto);
            session.setAttribute("loginUserName",dto.getUserName());
            return Map.of("registrationResult", registrationResult);
        }//同じuserNameが既に登録されている場合はfalseとをjavaScriptに返し他にないuserNameの場合はDBに挿入し、trueを返す。
    }
}
