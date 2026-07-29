package com.example.demo.Service;

import com.example.demo.DTO.TableDTO;
import com.example.demo.DTO.TableItemsViewDTO;
import com.example.demo.DTO.TableSearchDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    TableRepository repository;
    @InjectMocks
    SearchService service;
    List<TableDTO> dto = new ArrayList<>();
    @Test
    void findSearchedCheckListsTest(){
        LocalDate date = LocalDate.of(2026,1,1);
        createDto(1,"買い物",1,"リンゴ",true,date);
        createDto(1,"買い物",2,"みかん",false,date);
        when(repository.findSearchedCheckLists("abcdefg","%買い物%",2026,1,1))
                .thenReturn(dto);
        TableSearchDTO search = new TableSearchDTO("買い物","2026","1","1");
        List<TableViewDTO> view = service.findSearchedCheckLists("abcdefg",search);
        TableViewDTO shopping =view.get(0);
        checkListsEquals(shopping,1,"買い物","2026/1/1");
        ItemsEquals(shopping.getItems().get(0),1,"リンゴ",true);

    }
    //テスト用データをすぐにつくるためのメソッド
    private void createDto(Integer checkListsId, String checkListsName, Integer itemId, String itemNames, boolean isChecked, LocalDate createdAt){
        dto.add(new TableDTO(checkListsId,checkListsName,itemId,itemNames,isChecked,createdAt));
    }
    //結果の値と期待する値を比べてテストする。コードを読み取りやすくするためにこのメソッドを作っている。
    private void checkListsEquals(TableViewDTO checkList,Integer checkListsId,String checkListsName,String createdAt){
        assertEquals(checkListsId,checkList.getCheckListsId());
        assertEquals(checkListsName,checkList.getCheckListsName());
        assertEquals(createdAt,checkList.getCreatedAt());
    }
    //結果の値と期待する値を比べてテストする。コードを読み取りやすくするためにこのメソッドを作っている。
    private void ItemsEquals(TableItemsViewDTO items,Integer itemId, String itemName, Boolean isChecked){
        assertEquals(itemId,items.getItemId());
        assertEquals(itemName,items.getItemName());
        assertEquals(isChecked,items.getIsChecked());
    }
}
