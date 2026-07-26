package com.example.demo.Controller;

import com.example.demo.DTO.CheckDTO;
import com.example.demo.Service.UpdateCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
            /*
            mvc.perform(post("/updateCheck")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "isChecked":true,
                                    "itemId":1
                                    }
                                    """))
                    .andExpect(status().isOk());
             */
            CheckDTO dto = new CheckDTO();
            dto.setIsChecked(true);
            dto.setItemId(1);
            doThrow(new IllegalArgumentException("指定されたitemIdが見つかりません。"))
                    .when(service).saveIsChecked(any(CheckDTO.class));
            //saveIsCheckedが実行されたときにIllegalArgumentExceptionを投げる。
            mvc.perform(post("/updateCheck")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "isChecked":true,
                                    "itemId":1
                                    }
                                    """))
                    .andExpect(status().isBadRequest());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
