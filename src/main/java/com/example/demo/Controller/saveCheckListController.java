package com.example.demo.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class saveCheckListController {
    @PostMapping("/saveCheckListName")
    public ResponseEntity<?> saveCheckList(
            @Valid
    )
}
