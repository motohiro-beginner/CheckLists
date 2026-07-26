package com.example.demo.Controller;

import com.example.demo.DTO.TableItemsViewDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Service.TableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TableController.class)
public class TableControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    TableService service;
    @Test
    void Fetch(){
        try{
            List<TableViewDTO> viewDTO = new ArrayList<>();
            List<TableItemsViewDTO> items1 = new ArrayList<>();
            List<TableItemsViewDTO> items2 = new ArrayList<>();
            items1.add(new TableItemsViewDTO(1,"リンゴ",true));
            items1.add(new TableItemsViewDTO(2,"みかん",true));
            items1.add(new TableItemsViewDTO(3,"いちご",false));
            viewDTO.add(new TableViewDTO(1,"買い物",items1,"2026/01/01"));
            items2.add(new TableItemsViewDTO(4,"数学",true));
            items2.add(new TableItemsViewDTO(5,"国語",true));
            items2.add(new TableItemsViewDTO(6,"英語",false));
            viewDTO.add(new TableViewDTO(2,"勉強",items2,"2026/01/02"));
            /*テストデータ1
            when(service.existsByUserCheckLists("abcdefg"))
                    .thenReturn(true);
            when(service.findTableAllCheckLists("abcdefg"))
                    .thenReturn(viewDTO);
            mvc.perform(post("/Table")
                    .contentType(MediaType.APPLICATION_JSON)
                    .sessionAttr("userName","abcdefg")
                    .content("""
                            {
                            }
                            """))
                    .andExpect(status().isOk());
             */
            /*テストデータ2
            when(service.existsByUserCheckLists("abcdefg"))
                    .thenReturn(false);
            when(service.findTableAllCheckLists("abcdefg"))
                    .thenReturn(viewDTO);
            mvc.perform(post("/Table")
                            .contentType(MediaType.APPLICATION_JSON)
                            .sessionAttr("userName","abcdefg")
                            .content("""
                            {
                            }
                            """))
                    .andExpect(status().isNotFound());

             */
            when(service.existsByUserCheckLists("abcdefg"))
                    .thenReturn(false);
            when(service.findTableAllCheckLists("abcdefg"))
                    .thenReturn(viewDTO);
            mvc.perform(post("/Table")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                            {
                            }
                            """))
                    .andExpect(status().isBadRequest());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
