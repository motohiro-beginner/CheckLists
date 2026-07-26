package com.example.demo.Controller;

import com.example.demo.DTO.CheckDTO;
import com.example.demo.Service.UpdateCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UpdateCheckController.class)
public class UpdateCheckControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    UpdateCheckService service;
    @Test
    void updateCheckTest(){
        try {
            mvc.perform(post("/updateCheck")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "isChecked":true,
                                    "itemId":1
                                    }
                                    """))
                    .andExpect(status().isOk());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
