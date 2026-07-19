package com.example.demo.Service;

import com.example.demo.DTO.TableDTO;
import com.example.demo.DTO.TableViewDTO;
import com.example.demo.Repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TableServiceTest {
    @Mock
    TableRepository repository;
    @InjectMocks
    TableService service;
    @Test
    void findAllTableCheckListsTest(){
        LocalDate date1 = LocalDate.of(2026,1,1);
        LocalDate date2 = LocalDate.of(2026,1,2);
        List<TableDTO> dto = List.of(
                new TableDTO("買い物","リンゴ",true,date1),
                new TableDTO("買い物","みかん",true,date1),
                new TableDTO("買い物","いちご",false,date1),
                new TableDTO("勉強","数学",true,date2),
                new TableDTO("勉強","国語",false,date2),
                new TableDTO("勉強","英語",false,date2)
        );

        when(repository.findTableAllCheckLists("abcdefg"))
                .thenReturn(dto);
        /*テストデータ２
        List<TableViewDTO> viewDto = service.findTableAllCheckLists("aaaaaa");
        assertEquals(null,viewDto);*/
        /*テストデータ1
        List<TableViewDTO> viewDto = service.findTableAllCheckLists("abcdefg");


         */
        /*テストデータ１
        assertEquals("買い物",viewDto.get(0).getCheckListsName());
        assertEquals("リンゴ",viewDto.get(0).getItems().get(0).getItemName());
        assertEquals("みかん",viewDto.get(0).getItems().get(1).getItemName());
        assertEquals("いちご",viewDto.get(0).getItems().get(2).getItemName());
        assertEquals("2026/01/01",viewDto.get(0).getCreatedAt());
        assertEquals("勉強",viewDto.get(1).getCheckListsName());
        assertEquals("数学",viewDto.get(1).getItems().get(0).getItemName());
        assertEquals("国語",viewDto.get(1).getItems().get(1).getItemName());
        assertEquals("英語",viewDto.get(1).getItems().get(2).getItemName());
        assertEquals("2026/01/02",viewDto.get(1).getCreatedAt());
         */
    }
    @Test
    void existsByUserCheckLists(){
        when(repository.existsByUserCheckLists("abcdefg"))
                .thenReturn(0);
        boolean result = service.existsByUserCheckLists("abcdefg");
        assertFalse(result);
    }
}
