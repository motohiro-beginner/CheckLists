package com.example.demo.Controller;

import com.example.demo.Service.LoginService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    LoginService service;
    @Test
    void loginTest(){
        try {
            //テストデータ1
            /*
            when(service.existByUserNameAndPassword("abcdefg", "qwerty"))
                    .thenReturn(true);
            mvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "userName":"abcdefg",
                                    "password":"qwerty"
                                    }
                                    """))
                    .andExpect(status().isOk());
             */
            //テストデータ2
            when(service.existByUserNameAndPassword("abcdefg", "qwerty"))
                    .thenReturn(true);
            mvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "userName":"",
                                    "password":""
                                    }
                                    """))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0]").value("ユーザーIDを入力してください。"))
                    .andExpect(jsonPath("$[1]").value("パスワードを入力してください。"));

            /*
            //テストデータ3
            when(service.existByUserNameAndPassword("abcdefg", "qwerty"))
                    .thenReturn(false);
            mvc.perform(post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "userName":"abcdefg",
                                    "password":"qwerty"
                                    }
                                    """))
                    .andExpect(status().isBadRequest());
             */
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
