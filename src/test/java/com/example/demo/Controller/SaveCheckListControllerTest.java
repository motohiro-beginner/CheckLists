package com.example.demo.Controller;

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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(SaveCheckListController.class)
public class SaveCheckListControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    SaveCheckListService service;
    @Test
    void 問題なく更新できた場合(){
        try{
            SaveCheckListDTO dto = new SaveCheckListDTO();
            List<SaveItemsDTO> itemsDTO = new ArrayList<>();
            createItemsDTO(
                    itemsDTO,
                    "1",
                    "りんご",
                    true
            );
            createItemsDTO(
                    itemsDTO,
                    "2",
                    "みかん",
                    false
            );
            createDTO(
                    dto,
                    "1",
                    "買い物",
                    itemsDTO,
                    "2026",
                    "1",
                    "1"
            );
            ArgumentCaptor<SaveCheckListDTO> captor =
                    ArgumentCaptor.forClass(SaveCheckListDTO.class);
            mvc.perform(post("/saveCheckListName")
                    .contentType(MediaType.APPLICATION_JSON)
                    .sessionAttr())
            verify(service).updateColumns(captor.capture());
            SaveCheckListDTO argumentDTO = captor.getValue();
            argumentCheck(
                    argumentDTO,
                    "1",
                    "買い物",
                    "2026",
                    "1",
                    "1"
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
}
