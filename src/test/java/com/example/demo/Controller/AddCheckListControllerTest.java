package com.example.demo.Controller;

import com.example.demo.DTO.AddCheckListDTO;
import com.example.demo.DTO.AddItemsDTO;
import com.example.demo.Service.AddCheckListService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddCheckListController.class)
public class AddCheckListControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    AddCheckListService service;
    @Test
    void 問題なく更新できた場合(){
        try{
            ArgumentCaptor<String> userName =
                    ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<AddCheckListDTO> dto =
                    ArgumentCaptor.forClass(AddCheckListDTO.class);
            mvc.perform(post("/addCheckList")
                    .contentType(MediaType.APPLICATION_JSON)
                    .sessionAttr("userName","abcdefg")
                    .content("""
                             {
                             "checkListName": "買い物",
                             "items": [
                             {
                             "itemName": "りんご",
                             "isChecked": true
                             },
                             {
                             "itemName": "みかん",
                             "isChecked": false
                             }
                             ],
                             "createdAt": "2026/01/01"
                             }
                             """))
                    .andExpect(status().isOk());
            verify(service).addCheckList(userName.capture(),dto.capture());
            AddCheckListDTO correctDTO = new AddCheckListDTO();
            correctDTO.setCheckListName("買い物");
            AddItemsDTO itemsDTO = new AddItemsDTO();
            itemsDTO.setItemName("りんご");
            itemsDTO.setIsChecked(true);
            AddItemsDTO itemsDTO2 = new AddItemsDTO();
            itemsDTO2.setItemName("みかん");
            itemsDTO2.setIsChecked(false);
            correctDTO.setItems(List.of(itemsDTO,itemsDTO2));
            correctDTO.setCreatedAt("2026/01/01");
            argumentCheck(
                    userName.getValue(),
                    dto.getValue(),
                    "abcdefg",
                    correctDTO
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void 例外が発生した場合(){
        try{
            doThrow(new RuntimeException("チェックリスト新規作成処理に失敗しました。"))
                    .when(service).addCheckList(anyString(),any());
            mvc.perform(post("/addCheckList")
                            .contentType(MediaType.APPLICATION_JSON)
                            .sessionAttr("userName","abcdefg")
                            .content("""
                             {
                             "checkListName": "買い物",
                             "items": [
                             {
                             "itemName": "りんご",
                             "isChecked": true
                             },
                             {
                             "itemName": "みかん",
                             "isChecked": false
                             }
                             ],
                             "createdAt": "2026/01/01"
                             }
                             """))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("チェックリスト新規作成処理に失敗しました。"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void セッション情報が消失した場合(){
        try{
            mvc.perform(post("/addCheckList")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                             {
                             "checkListName": "買い物",
                             "items": [
                             {
                             "itemName": "りんご",
                             "isChecked": true
                             },
                             {
                             "itemName": "みかん",
                             "isChecked": false
                             }
                             ],
                             "createdAt": "2026/01/01"
                             }
                             """))
                    .andExpect(status().isUnauthorized())
                    .andExpect(content().string("ユーザー名が消失したため、ログイン画面に戻りました。"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void argumentCheck(String userName, AddCheckListDTO dto, String correctUserName,AddCheckListDTO correctDTO){
        assertEquals(userName,correctUserName);
        assertEquals(dto.getCheckListName(),correctDTO.getCheckListName());
        for(int i=0;i<dto.getItems().size();i++){
            assertEquals(dto.getItems().get(i).getItemName(),correctDTO.getItems().get(i).getItemName());
            assertEquals(dto.getItems().get(i).getIsChecked(),correctDTO.getItems().get(i).getIsChecked());
        }
        assertEquals(dto.getCreatedAt(),correctDTO.getCreatedAt());
    }
}
