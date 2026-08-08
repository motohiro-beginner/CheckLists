package com.example.demo.Service;

import com.example.demo.DTO.SaveCheckListDTO;
import com.example.demo.DTO.SaveItemsDTO;
import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveCheckListServiceTest {
    @Mock
    OneCheckListRepository repository;
    @Mock
    OneCheckListItemsRepository iRepository;
    @InjectMocks
    SaveCheckListService service;
    @Test
    void 問題なくこうしんできたばあい(){
        SaveCheckListDTO dto = new SaveCheckListDTO();
        List<SaveItemsDTO> itemDTO = new ArrayList<>();
        createItemsDTO(itemDTO,"1","リンゴ",true);
        createItemsDTO(itemDTO,"2","みかん",false);
        createDTO(dto,"1","買い物",itemDTO,"2026","1","1");
        ArgumentCaptor<OneCheckListCheckListsEntity> captor =
                ArgumentCaptor.forClass(OneCheckListCheckListsEntity.class);
        ArgumentCaptor<OneCheckListItemsEntity> captor2 =
                ArgumentCaptor.forClass(OneCheckListItemsEntity.class);
        service.updateColumns(dto);
        verify(repository).save(captor.capture());
        verify(iRepository,times(2)).save(captor2.capture());
        entityCheck(captor.getValue(),1,"買い物",LocalDate.of(2026,1,1));
        entityCheck2(captor2.getAllValues().get(1),1,"リンゴ",true);
        entityCheck2(captor2.getAllValues().get(2),2,"みかん",false);
    }
    private void createDTO(SaveCheckListDTO dto, String checkListsId, String checkListName, List<SaveItemsDTO> items,String year,String month,String day){
        dto.setCheckListsId(checkListsId);
        dto.setCheckListName(checkListName);
        dto.setItems(items);
        dto.setYear(year);
        dto.setMonth(month);
        dto.setDay(day);
    }
    private void createItemsDTO(List<SaveItemsDTO> dto,String itemId,String itemNames,boolean isChecked){
        SaveItemsDTO items = new SaveItemsDTO();
        items.setItemId(itemId);
        items.setItemNames(itemNames);
        items.setIsChecked(isChecked);
        dto.add(items);
    }
    private void entityCheck(OneCheckListCheckListsEntity value, Integer checkListId, String checkListName,LocalDate createdAt){
        assertEquals(checkListId,value.getCheckListsId());
        assertEquals(checkListName,value.getCheckListsName());
        assertEquals(createdAt,value.getCreatedAt());
    }
    private void entityCheck2(OneCheckListItemsEntity value,Integer itemId,String itemName,boolean isChecked){
        assertEquals(itemId,value.getItemId());
        assertEquals(itemName,value.getItemName());
        assertEquals(isChecked,value.getIsChecked());
    }
}
