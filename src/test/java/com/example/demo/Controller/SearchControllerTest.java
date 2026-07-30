package com.example.demo.Controller;

import com.example.demo.DTO.TableItemsViewDTO;
import com.example.demo.DTO.TableSearchDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SearchController.class)
public class SearchControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    SearchService service;
    @Test
    void 検索条件が何もない場合(){
        try {
            List<TableViewDTO> testData = new ArrayList<>();
            List<TableItemsViewDTO> itemTestData = new ArrayList<>();
            createItemData(itemTestData, "1", "リンゴ", true);
            createItemData(itemTestData, "2", "みかん", false);
            createListData(testData, "1", "買い物", itemTestData, "2026/01/01");
            List<TableItemsViewDTO> itemTestData2 = new ArrayList<>();
            createItemData(itemTestData2, "3", "国語", true);
            createItemData(itemTestData2, "4", "数学", true);
            createListData(testData, "2", "勉強", itemTestData2, "2026/01/02");
            TableSearchDTO dto = new TableSearchDTO(null, null, null, null);
            when(service.existsBySearchedCheckLists("abcdefg", dto))
                    .thenReturn(true);
            when(service.findSearchedCheckLists("abcdefg", dto))
                    .thenReturn(testData);
            mvc.perform(post("/search")
                            .contentType(MediaType.APPLICATION_JSON)
                            .sessionAttr("userName", "abcdefg")
                            .content("""
                                    {
                                    }
                                    """))
                    .andExpect(status().isOk());
            verify(service,times(1))
                    .existsBySearchedCheckLists("abcdefg",dto);
            verify(service,times(1))
                    .findSearchedCheckLists("abcdefg",dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void チェックリスト名が指定されている場合(){
        try {
            List<TableViewDTO> testData = new ArrayList<>();
            List<TableItemsViewDTO> itemTestData = new ArrayList<>();
            createItemData(itemTestData, "1", "リンゴ", true);
            createItemData(itemTestData, "2", "みかん", false);
            createListData(testData, "1", "買い物", itemTestData, "2026/01/01");
            when(service.existsBySearchedCheckLists(
                    eq("abcdefg"),
                    ))
                    .thenReturn(true);
            when(service.findSearchedCheckLists(
                    eq("abcdefg"),
                    argThat(dto ->
                            "買い物".equals(dto.getSearchName()) &&
                                    dto.getYearSearch() == null &&
                                    dto.getMonthSearch() == null &&
                                    dto.getDaySearch() == null
                    )))
                    .thenReturn(testData);
            mvc.perform(post("/search")
                            .contentType(MediaType.APPLICATION_JSON)
                            .sessionAttr("userName", "abcdefg")
                            .content("""
                                    {
                                    "searchName":"買い物"
                                    }
                                    """))
                    .andExpect(status().isOk());
            verify(service,times(1))
                    .existsBySearchedCheckLists("abcdefg",dto);
            verify(service,times(1))
                    .findSearchedCheckLists("abcdefg",dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void createListData(List<TableViewDTO> testData, String listId, String listName, List<TableItemsViewDTO> items,String createdAt){
        testData.add(new TableViewDTO(listId,listName,items,createdAt));
    }
    private void createItemData(List<TableItemsViewDTO> itemTestData,String itemId,String itemName,boolean isChecked){
        itemTestData.add(new TableItemsViewDTO(itemId,itemName,isChecked));
    }
}
