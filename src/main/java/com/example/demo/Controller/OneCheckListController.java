package com.example.demo.Controller;

import com.example.demo.Service.OneCheckListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneCheckListController {
    private final OneCheckListService service;
    public OneCheckListController(OneCheckListService service){
        this.service = service;
    }
    @PostMapping("/showOneCheckList/{checkListsId}")
    public ResponseEntity<?> showOneCheckList(
            @PathVariable Integer checkListsId
    ){

    }
}
