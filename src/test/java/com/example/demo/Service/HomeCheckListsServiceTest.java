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
        LocalDate testDateTime = LocalDate.of(2026, 1, 1);
        List<HomeCheckListsDTO> checkLists = new ArrayList<>();
        /*テストデータ1
        checkLists.add(new HomeCheckListsDTO("買い物","リンゴ",true,testDateTime));
        checkLists.add(new HomeCheckListsDTO("買い物","みかん",true,testDateTime));
        checkLists.add(new HomeCheckListsDTO("買い物","イチゴ",false,testDateTime));
        checkLists.add(new HomeCheckListsDTO("勉強","国語",false,testDateTime));
        checkLists.add(new HomeCheckListsDTO("勉強","数学",false,testDateTime));
        checkLists.add(new HomeCheckListsDTO("勉強","英語",false,testDateTime));
        */
        when(repository.findAllCheckLists("abcdefg",testDateTime))
                .thenReturn(checkLists);
        List<HomeCheckListsViewDTO> viewCheckLists = service.findAllCheckLists("abcdefg");
        /*テストデータ2
        assertEquals(null,viewCheckLists);
        */
        /*テストデータ1
        assertEquals("買い物",viewCheckLists.get(0).getCheckListsName());
        assertEquals("リンゴ",viewCheckLists.get(0).getItems().get(0).getItemNames());
        assertEquals(true,viewCheckLists.get(0).getItems().get(0).getIsChecked());
        assertEquals("みかん",viewCheckLists.get(0).getItems().get(1).getItemNames());
        assertEquals(true,viewCheckLists.get(0).getItems().get(1).getIsChecked());
        assertEquals("イチゴ",viewCheckLists.get(0).getItems().get(2).getItemNames());
        assertEquals(false,viewCheckLists.get(0).getItems().get(2).getIsChecked());
        assertEquals("2026/01/01",viewCheckLists.get(0).getCreatedAt());

        assertEquals("勉強",viewCheckLists.get(1).getCheckListsName());
        assertEquals("国語",viewCheckLists.get(1).getItems().get(0).getItemNames());
        assertEquals(false,viewCheckLists.get(1).getItems().get(0).getIsChecked());
        assertEquals("数学",viewCheckLists.get(1).getItems().get(1).getItemNames());
        assertEquals(false,viewCheckLists.get(1).getItems().get(1).getIsChecked());
        assertEquals("英語",viewCheckLists.get(1).getItems().get(2).getItemNames());
        assertEquals(false,viewCheckLists.get(1).getItems().get(2).getIsChecked());
        assertEquals("2026/01/01",viewCheckLists.get(1).getCreatedAt());
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
