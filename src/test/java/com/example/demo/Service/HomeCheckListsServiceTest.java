package com.example.demo.Service;

import com.example.demo.Repository.HomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)//JUnitでMockitoを使えるようにする設定
public class HomeCheckListsServiceTest {
    @Mock
    HomeRepository repository;
    @InjectMocks
    HomeCheckListsService service;
    @Test
    void findAllCheckListsTest(){
        LocalDateTime testDateTime = LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0);
        when(repository.findAllCheckLists("abcdefg",testDateTime));
    }
}
