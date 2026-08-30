package com.example.demo.Controller;

import com.example.demo.DTO.NewItemsDTO;
import com.example.demo.DTO.SaveCheckListDTO;
import com.example.demo.DTO.SaveItemsDTO;
import com.example.demo.Service.SaveCheckListService;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaveCheckListController.class)
public class SaveCheckListControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    SaveCheckListService service;
    @Test
    void 問題なく更新できた場合(){
        try{
            ArgumentCaptor<SaveCheckListDTO> captor =
                    ArgumentCaptor.forClass(SaveCheckListDTO.class);
            mvc.perform(post("/saveCheckListName")
                    .contentType(MediaType.APPLICATION_JSON)
                    .sessionAttr("userName","abcdefg")
                    .content("""
                            {
                             "checkListsId": "1",
                             "checkListName": "買い物",
                             "items": [
                             {
                              "itemId": "1",
                              "itemNames": "りんご",
                              "isChecked": true
                             },
                            {
                             "itemId": "2",
                             "itemNames": "みかん",
                             "isChecked": false
                            }
                            ],
                            "newItems": [
                            {
                            "itemName": "いちご",
                            "isChecked": true
                            }
                            ],
                            "year": "2026",
                            "month": "01",
                            "day": "01"
                            }
                            """))
                            .andExpect(status().isOk());
            verify(service).updateColumns(captor.capture());
            SaveCheckListDTO argumentDTO = captor.getValue();
            argumentCheck(
                    argumentDTO,
                    "1",
                    "買い物",
                    "2026",
                    "01",
                    "01"
            );
            List<SaveItemsDTO> items = argumentDTO.getItems();
            argumentCheck2(
                    items.get(0),
                    "1",
                    "りんご",
                    true
            );
            argumentCheck2(
                    items.get(1),
                    "2",
                    "みかん",
                    false
            );
            List<NewItemsDTO> newItems = argumentDTO.getNewItems();
            argumentCheck3(
                    newItems.get(0),
                    "いちご",
                    true
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void セッション情報が消失していた場合(){
        try {
            mvc.perform(post("/saveCheckListName")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                             "checkListsId": "1",
                             "checkListName": "買い物",
                             "items": [
                             {
                              "itemId": "1",
                              "itemNames": "りんご",
                              "isChecked": true
                             },
                            {
                             "itemId": "2",
                             "itemNames": "みかん",
                             "isChecked": false
                            }
                            ],
                            "newItems": [
                            {
                            "itemName": "いちご",
                            "isChecked": true
                            }
                            ],
                            "year": "2026",
                            "month": "01",
                            "day": "01"
                            }
                            """))
                .andExpect(status().isUnauthorized())
                    .andExpect(content().string("ユーザー名が消失したため、ログイン画面に戻りました。"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void 更新処理に失敗した場合(){
        try{
            doThrow(new RuntimeException("データの保存に失敗しました。"))
                    .when(service)
                    .updateColumns(any(SaveCheckListDTO.class));
            mvc.perform(post("/saveCheckListName")
                    .contentType(MediaType.APPLICATION_JSON)
                    .sessionAttr("userName","abcdefg")
                    .content("""
                            {
                             "checkListsId": "1",
                             "checkListName": "買い物",
                             "items": [
                             {
                              "itemId": "1",
                              "itemNames": "りんご",
                              "isChecked": true
                             },
                            {
                             "itemId": "2",
                             "itemNames": "みかん",
                             "isChecked": false
                            }
                            ],
                            "newItems": [
                            {
                            "itemName": "いちご",
                            "isChecked": true
                            }
                            ],
                            "year": "2026",
                            "month": "01",
                            "day": "01"
                            }
                            """))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("データの保存に失敗しました。"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void createDTO(SaveCheckListDTO dto, String checkListsId, String checkListName, List<SaveItemsDTO> items, String year, String month, String day){
        dto.setCheckListsId(checkListsId);
        dto.setCheckListName(checkListName);
        dto.setItems(items);
        dto.setYear(year);
        dto.setMonth(month);
        dto.setDay(day);
    }
    private void createItemsDTO(List<SaveItemsDTO> dto,String itemId,String itemNames,boolean isChecked){
        SaveItemsDTO items = new SaveItemsDTO();
        items.setItemId(itemId);
        items.setItemNames(itemNames);
        items.setIsChecked(isChecked);
        dto.add(items);
    }
    //SaveCheckListDTOの中の変数の値をチェックする。
    private void argumentCheck(SaveCheckListDTO dto,String checkListsId,String checkListName,String year,String month,String day){
        assertEquals(checkListsId,dto.getCheckListsId());
        assertEquals(checkListName,dto.getCheckListName());
        assertEquals(year,dto.getYear());
        assertEquals(month,dto.getMonth());
        assertEquals(day,dto.getDay());
    }
    //SaveCheckListDTOの中のSaveItemsDTOの変数の値をチェックする。
    private void argumentCheck2(SaveItemsDTO dto,String itemId,String itemNames,boolean isChecked){
        assertEquals(itemId,dto.getItemId());
        assertEquals(itemNames,dto.getItemNames());
        assertEquals(isChecked,dto.getIsChecked());
    }
    private void argumentCheck3(NewItemsDTO dto,String itemName,boolean isChecked){
        assertEquals(itemName,dto.getItemName());
        assertEquals(isChecked,dto.getIsChecked());
    }
}
