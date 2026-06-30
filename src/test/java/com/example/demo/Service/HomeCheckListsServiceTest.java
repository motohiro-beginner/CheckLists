package com.example.demo.Service;

import com.example.demo.Repository.HomeRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)//JUnitでMockitoを使えるようにする設定
public class HomeCheckListsServiceTest {
    @Mock
    HomeRepository repository;
    @InjectMocks
    HomeCheckListsService service;
}
