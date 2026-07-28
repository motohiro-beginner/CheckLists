package com.example.demo.Service;

import com.example.demo.DTO.HomeCheckListsDTO;
import com.example.demo.DTO.HomeCheckListsViewDTO;
import com.example.demo.Repository.HomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)//JUnitでMockitoを使えるようにする設定
public class HomeCheckListsServiceTest {
    @Mock
    HomeRepository repository;
    @InjectMocks
    HomeCheckListsService service;
    @Test
    void findAllCheckListsTest(){
        LocalDate testDateTime = LocalDate.now();
        List<HomeCheckListsDTO> checkLists = new ArrayList<>();
        String testDateTimeStr = testDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        checkLists.add(new HomeCheckListsDTO(1,"買い物",1,"リンゴ",true,testDateTime));
        checkLists.add(new HomeCheckListsDTO(1,"買い物",2,"みかん",true,testDateTime));
        checkLists.add(new HomeCheckListsDTO(1,"買い物",3,"イチゴ",false,testDateTime));
        checkLists.add(new HomeCheckListsDTO(2,"勉強",4,"国語",false,testDateTime));
        checkLists.add(new HomeCheckListsDTO(2,"勉強",5,"数学",false,testDateTime));
        checkLists.add(new HomeCheckListsDTO(2,"勉強",6,"英語",false,testDateTime));

        when(repository.findAllCheckLists("abcdefg",testDateTime))
                .thenReturn(checkLists);
        List<HomeCheckListsViewDTO> viewCheckLists = service.findAllCheckLists("abcdefg");
        /*テストデータ2
        assertEquals(null,viewCheckLists);
        */
        /*テストデータ1
        assertEquals("1",viewCheckLists.get(0).getCheckListsId());
        assertEquals("買い物",viewCheckLists.get(0).getCheckListsName());

        assertEquals("1",viewCheckLists.get(0).getItems().get(0).getItemId());
        assertEquals("リンゴ",viewCheckLists.get(0).getItems().get(0).getItemNames());
        assertEquals(true,viewCheckLists.get(0).getItems().get(0).getIsChecked());

        assertEquals("2",viewCheckLists.get(0).getItems().get(1).getItemId());
        assertEquals("みかん",viewCheckLists.get(0).getItems().get(1).getItemNames());
        assertEquals(true,viewCheckLists.get(0).getItems().get(1).getIsChecked());

        assertEquals("3",viewCheckLists.get(0).getItems().get(2).getItemId());
        assertEquals("イチゴ",viewCheckLists.get(0).getItems().get(2).getItemNames());
        assertEquals(false,viewCheckLists.get(0).getItems().get(2).getIsChecked());

        assertEquals(testDateTimeStr,viewCheckLists.get(0).getCreatedAt());

        assertEquals("2",viewCheckLists.get(1).getCheckListsId());
        assertEquals("勉強",viewCheckLists.get(1).getCheckListsName());
        assertEquals("4",viewCheckLists.get(1).getItems().get(0).getItemId());
        assertEquals("国語",viewCheckLists.get(1).getItems().get(0).getItemNames());
        assertEquals(false,viewCheckLists.get(1).getItems().get(0).getIsChecked());
        assertEquals("5",viewCheckLists.get(1).getItems().get(1).getItemId());
        assertEquals("数学",viewCheckLists.get(1).getItems().get(1).getItemNames());
        assertEquals(false,viewCheckLists.get(1).getItems().get(1).getIsChecked());
        assertEquals("6",viewCheckLists.get(1).getItems().get(2).getItemId());
        assertEquals("英語",viewCheckLists.get(1).getItems().get(2).getItemNames());
        assertEquals(false,viewCheckLists.get(1).getItems().get(2).getIsChecked());
        assertEquals(testDateTimeStr,viewCheckLists.get(1).getCreatedAt());
         */
    }
    @Test
    void existsByUserNameAndCreatedAtTest(){
        LocalDate testDateTime = LocalDate.now();
        when(repository.existsByUserNameAndCreatedAt("abcdefg",testDateTime))
                .thenReturn(1);
        boolean success = service.existsByUserNameAndCreatedAt("abcdefg");
        assertTrue(success);
    }
}
