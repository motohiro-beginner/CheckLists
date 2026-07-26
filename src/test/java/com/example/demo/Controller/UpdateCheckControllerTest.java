package com.example.demo.Controller;

import com.example.demo.Service.UpdateCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UpdateCheckController.class)
public class UpdateCheckControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    UpdateCheckService service;
    void updateCheckTest(){

    }
}
