package com.example.demo.Controller;

import com.example.demo.DTO.TableItemsViewDTO;
import com.example.demo.DTO.TableSearchDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    SearchService service;
    @Test
    void searchTest(){
        List<TableViewDTO> testData = new ArrayList<>();
        List<TableItemsViewDTO> itemTestData = new ArrayList<>();
        createItemData(itemTestData,"1","リンゴ",true);
        TableSearchDTO dto = new TableSearchDTO(null,null,null,null);
        when(service.existsBySearchedCheckLists("abcdefg",dto))
                .thenReturn(true);

    }
    private void createListData(List<TableViewDTO> testData, String listId, String listName, List<TableItemsViewDTO> items,String createdAt){
        testData.add(new TableViewDTO(listId,listName,items,createdAt));
    }
    private void createItemData(List<TableItemsViewDTO> itemTestData,String itemId,String itemName,boolean isChecked){
        itemTestData.add(new TableItemsViewDTO(itemId,itemName,isChecked));
    }
}
