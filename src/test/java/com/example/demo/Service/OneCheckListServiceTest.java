package com.example.demo.Service;

import com.example.demo.DTO.OneCheckListDTO;
import com.example.demo.DTO.OneCheckListViewDTO;
import com.example.demo.DTO.OneItemsViewDTO;
import com.example.demo.Repository.OneCheckListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OneCheckListServiceTest {
    @Mock
    OneCheckListRepository repository;
    @InjectMocks
    OneCheckListService service;
    @Test
    void 問題なく実行できた場合(){
        List<OneCheckListDTO> dto = new ArrayList<>();
        createDTO(dto,1,"買い物",1,"りんご",true,LocalDate.of(2026,1,1));
        createDTO(dto,1,"買い物",2,"みかん",false,LocalDate.of(2026,1,1));
        when(repository.findOneCheckList(1))
                .thenReturn(dto);
        OneCheckListViewDTO result = service.findOneCheckList(1,"abcdefg");
        assertEquals("1",result.getCheckListId());
        assertEquals("買い物",result.getCheckListName());
        assertEquals("1",result.getItems().get(0).getItemId());
        assertEquals("りんご",result.getItems().get(0).getItemName());
        assertTrue(result.getItems().get(0).getIsChecked());
        assertEquals("2",result.getItems().get(1).getItemId());
        assertEquals("みかん",result.getItems().get(1).getItemName());
        assertFalse(result.getItems().get(1).getIsChecked());
        assertEquals("2026/01/01",result.getCreatedAt());
    }
    @Test
    void RuntimeExceptionが発生した場合(){
        List<OneCheckListDTO> dto = new ArrayList<>();
        createDTO(dto,1,"買い物",1,"りんご",true,LocalDate.of(2026,1,1));
        createDTO(dto,1,"買い物",2,"みかん",false,LocalDate.of(2026,1,1));
                when(repository.existsByCheckListIdAndUserName(1,"abcdefg"))
                        .thenReturn(0);
        RuntimeException exception = assertThrows(RuntimeException.class,() -> {
            service.findOneCheckList(1,"abcdefg");
        });
        exceptionCheck("参照するチェックリストにアクセスする権限がありません。チェックリストIDを改ざんしましたね?",exception.getMessage());
    }
    private void createDTO(List<OneCheckListDTO> dto, Integer checkListId, String checkListName, Integer itemId, String itemName, boolean isChecked, LocalDate createdAt){
        dto.add(new OneCheckListDTO(checkListId,checkListName,itemId,itemName,isChecked,createdAt));
    }
    private void exceptionCheck(String correctMessage,String actualMessage){
        assertEquals(correctMessage,actualMessage);
    }
}
