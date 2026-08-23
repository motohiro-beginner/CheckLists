package com.example.demo.Controller;

import com.example.demo.DTO.OneCheckListViewDTO;
import com.example.demo.DTO.OneItemsViewDTO;
import com.example.demo.Service.OneCheckListService;
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

@WebMvcTest(OneCheckListController.class)
public class OneCheckListControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    OneCheckListService service;
    @Test
    void 問題なく実行できた場合(){
        try {
            List<OneItemsViewDTO> items = new ArrayList<>();
            items.add(createItemsDTO("1", "りんご", true));
            items.add(createItemsDTO("2", "みかん", false));
            OneCheckListViewDTO list = createDTO("1", "買い物", items, "2026/01/01");
            when(service.findOneCheckList(1))
                    .thenReturn(list);
            mvc.perform(post("/showOneCheckList/{checkListsId}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .sessionAttr("userName", "abcdefg"))
                    .andExpect(status().isOk());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void ユーザー名が消失していた場合(){
        try {
            List<OneItemsViewDTO> items = new ArrayList<>();
            items.add(createItemsDTO("1", "りんご", true));
            items.add(createItemsDTO("2", "みかん", false));
            OneCheckListViewDTO list = createDTO("1", "買い物", items, "2026/01/01");
            when(service.findOneCheckList(1))
                    .thenReturn(list);
            mvc.perform(post("/showOneCheckList/{checkListsId}", 1)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private OneCheckListViewDTO createDTO(String checkListId, String checkListName, List<OneItemsViewDTO> items, String createdAt){
        return new OneCheckListViewDTO(checkListId,checkListName,items,createdAt);
    }
    private OneItemsViewDTO createItemsDTO(String itemId,String itemName,boolean isChecked){
        return new OneItemsViewDTO(itemId,itemName,isChecked);
    }
}
