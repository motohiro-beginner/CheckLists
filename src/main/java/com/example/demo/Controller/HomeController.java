package com.example.demo.Controller;

import com.example.demo.DTO.HomeCheckListsViewDTO;
import com.example.demo.Service.HomeCheckListsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import java.util.List;

@RestController
public class HomeController {
    @PostMapping("/home")
    public ResponseEntity<?> home(HttpSession session){
        HomeCheckListsService service = new HomeCheckListsService();
        LocalDateTime nowDate = LocalDateTime.now();
        List<HomeCheckListsViewDTO> resultCheckLists = service.findAllCheckLists((String)session.getAttribute("userName"),nowDate);
        return ResponseEntity.ok(resultCheckLists);
    }
}
