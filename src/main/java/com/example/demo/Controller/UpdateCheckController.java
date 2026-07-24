package com.example.demo.Controller;

import com.example.demo.DTO.CheckDTO;
import com.example.demo.Service.UpdateCheckService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateCheckController {
    private UpdateCheckService service;
    public UpdateCheckController(UpdateCheckService service){
        this.service = service;
    }
    public ResponseEntity<?> updateCheck(
            @Valid @RequestBody CheckDTO dto
    ){

    }
}
