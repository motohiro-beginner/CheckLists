package com.example.demo.Controller;

import com.example.demo.DTO.HomeCheckListsViewDTO;
import com.example.demo.DTO.HomeItemsViewDTO;
import com.example.demo.Service.HomeCheckListsService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @Autowired
    MockMvc mvc;
    @MockitoBean
    HomeCheckListsService service;
    @Test
    void Fetch(){
        try {
            List<HomeCheckListsViewDTO> viewCheckLists = new ArrayList<>();
            List<HomeItemsViewDTO> items1 = new ArrayList<>();
            List<HomeItemsViewDTO> items2 = new ArrayList<>();
            items1.add(new HomeItemsViewDTO("リンゴ", true));
            items1.add(new HomeItemsViewDTO("みかん", true));
            items1.add(new HomeItemsViewDTO("いちご", false));
            viewCheckLists.add(new HomeCheckListsViewDTO("買い物", items1, "2026/01/01"));
            items2.add(new HomeItemsViewDTO("数学", true));
            items2.add(new HomeItemsViewDTO("国語", true));
            items2.add(new HomeItemsViewDTO("英語", false));
            viewCheckLists.add(new HomeCheckListsViewDTO("勉強", items2, "2026/01/01"));
            /*テストデータ１
            when(service.existsByUserNameAndCreatedAt("abcdefg"))
                    .thenReturn(true);
            //Controllerがservice.existsByUserNameAndCreatedAtをよびだしたら、trueを返す。
            when(service.findAllCheckLists("abcdefg"))
                    .thenReturn(viewCheckLists);
            //Controllerがservice.findAllCheckListsをよびだしたら、viewCheckListsを返す。
            mvc.perform(post("/home")
                    //偽のPOST送信を作る。これによりControllerのhomeメソッドを起動させる。
                    .contentType(MediaType.APPLICATION_JSON)
                    //JSON形式で送る。
                    .sessionAttr("userName","abcdefg")
                    //セッション情報を送る。
                    .content("""
                            {
                            }
                            """))
                    //JSONの中身を表す。
                    .andExpect(status().isOk());
                    //応答結果がResponseEntity.ok()かどうかを確かめる。
             */
            /*テストデータ2
            when(service.existsByUserNameAndCreatedAt("abcdefg"))
                    .thenReturn(false);
            //Controllerがservice.existsByUserNameAndCreatedAtをよびだしたら、falseを返す。
            mvc.perform(post("/home")
                    .contentType(MediaType.APPLICATION_JSON)
                    .sessionAttr("userName","abcdefg")
                    .content("""
                            {
                            }
                            """))
                    .andExpect(status().isNotFound());
                    //応答結果がResponseEntity.notFound().build()かどうかを確かめる。

             */
            /*テストデータ3
            when(service.existsByUserNameAndCreatedAt("abcdefg"))
                    .thenReturn(true);
            //Controllerがservice.existsByUserNameAndCreatedAtをよびだしたら、trueを返す。
            mvc.perform(post("/home")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                            {
                            }
                            """))
                    .andExpect(status().isBadRequest());
            //応答結果がResponseEntity.notFound().build()かどうかを確かめる。
             */
            /*テストデータ4
            mvc.perform(post("/home")
                            .contentType(MediaType.APPLICATION_JSON)
                            .sessionAttr("userName",null)
                            .content("""
                            {
                            }
                            """))
                    .andExpect(status().isBadRequest());
            //応答結果がResponseEntity.badRequest()かどうかを確かめる。
             */
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
