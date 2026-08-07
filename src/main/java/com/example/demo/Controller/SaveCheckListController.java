package com.example.demo.Controller;

import com.example.demo.DTO.SaveCheckListDTO;
import com.example.demo.Service.SaveCheckListService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveCheckListController {
    private SaveCheckListService service;
    public SaveCheckListController(SaveCheckListService service){
        this.service = service;
    }
    @PostMapping("/saveCheckListName")
    public ResponseEntity<?> saveCheckList(
            @Valid @RequestBody SaveCheckListDTO dto
    ){

    }
}
