package com.example.demo.Controller;

import com.example.demo.DTO.NewRegistrationDTO;
import com.example.demo.Service.NewRegistrationService;
import org.apache.tomcat.util.descriptor.web.FragmentJarScannerCallback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class NewRegistrationControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    NewRegistrationService service;
    @Test
    void newRegistrationTest(){
        try {
            /*テストデータ1
            when(service.existsByUserName("abcdefg"))
                    .thenReturn(false);
            doNothing().when(service).save(any(NewRegistrationDTO.class));
            mvc.perform(post("/newRegistration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                            "userName":"abcdefg",
                            "password":"qwerty12"
                            }
                            """))
                    .andExpect(status().isOk());
                    //登録成功した場合をテストする。
*/
            /*
            when(service.existsByUserName("abcdefg"))
                    .thenReturn(true);
            doNothing().when(service).save(any(NewRegistrationDTO.class));
            mvc.perform(post("/newRegistration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                            {
                            "userName":"abcdefg",
                            "password":"qwerty12"
                            }
                            """))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("ユーザー名は既に他の人に使われています。"));
                    //ユーザー名が既に使用されていた場合をテストする。
             */

                    doThrow(new IllegalArgumentException("ユーザー名は既に使用されています。"))
                            .when(service)
                            .save(any(NewRegistrationDTO.class));
                    //saveメソッドがIllegalArgumentExceptionを投げたときをテストする。
            mvc.perform(post("/newRegistration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                            "userName":"abcdefg",
                            "password":"qwerty12"
                            }
                            """))
                    .andExpect(status().isConflict())
                    .andExpect(content().string("ユーザー名は既に使用されています。"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
