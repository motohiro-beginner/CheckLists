package com.example.demo.Service;

import com.example.demo.DTO.CheckDTO;
import com.example.demo.Entity.HomeCheckListsEntity;
import com.example.demo.Entity.HomeCheckListsItemsEntity;
import com.example.demo.Repository.HomeItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCheckServiceTest {
    @Mock
    HomeItemsRepository repository;
    @InjectMocks
    UpdateCheckService service;
    @Test
    void updateCheckTest(){
        /*テストデータ1
        HomeCheckListsItemsEntity item = new HomeCheckListsItemsEntity();
        item.setItemId(1);
        item.setIsChecked(true);
        when(repository.findById(1))
                .thenReturn(Optional.of(item));
        ArgumentCaptor<HomeCheckListsItemsEntity> captor =
                ArgumentCaptor.forClass(HomeCheckListsItemsEntity.class);
        CheckDTO dto = new CheckDTO();
        dto.setIsChecked(true);
        dto.setItemId(1);
        service.saveIsChecked(dto);
        verify(repository).save(captor.capture());
        HomeCheckListsItemsEntity savedEntity = captor.getValue();
        assertTrue(savedEntity.getIsChecked());
        assertEquals(1,savedEntity.getItemId());
         */
        /*テストデータ2
        when(repository.findById(1))
                .thenReturn(Optional.empty());
        CheckDTO dto = new CheckDTO();
        dto.setIsChecked(true);
        dto.setItemId(1);
        assertThrows(IllegalArgumentException.class,
                () -> service.saveIsChecked(dto));
         */
        HomeCheckListsItemsEntity item = new HomeCheckListsItemsEntity();
        item.setItemId(1);
        item.setIsChecked(true);
        when(repository.findById(1))
                .thenReturn(Optional.of(item));
        ArgumentCaptor<HomeCheckListsItemsEntity> captor =
                ArgumentCaptor.forClass(HomeCheckListsItemsEntity.class);
        CheckDTO dto = new CheckDTO();
        dto.setIsChecked(false);
        dto.setItemId(1);
        service.saveIsChecked(dto);
        verify(repository).save(captor.capture());
        HomeCheckListsItemsEntity savedEntity = captor.getValue();
        assertFalse(savedEntity.getIsChecked());
        assertEquals(1,savedEntity.getItemId());
    }
}
