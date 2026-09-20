package com.example.demo.Service;

import com.example.demo.DTO.AddCheckListDTO;
import com.example.demo.DTO.AddItemsDTO;
import com.example.demo.Entity.AddCheckListEntity;
import com.example.demo.Entity.AddItemsEntity;
import com.example.demo.Entity.AddUserEntity;
import com.example.demo.Repository.AddCheckListRepository;
import com.example.demo.Repository.AddItemsRepository;
import com.example.demo.Repository.AddUserRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddCheckListServiceTest {
    @Mock
    AddUserRepository userRepository;
    @Mock
    AddCheckListRepository checkListRepository;
    @Mock
    AddItemsRepository itemRepository;
    @InjectMocks
    AddCheckListService service;
    @Test
    void 問題なく実行できた場合(){
        List<AddItemsDTO> items = new ArrayList<>();
        createItemsDTO(items,"りんご",true);
        createItemsDTO(items,"みかん",false);
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"買い物",items,"2026/01/01");
        AddUserEntity user = createUserEntity("abcdefg","qwerty");
        when(userRepository.findByUserName("abcdefg"))
                .thenReturn(user);
        service.addCheckList("abcdefg",dto);
        ArgumentCaptor<AddCheckListEntity> captor =
                ArgumentCaptor.forClass(AddCheckListEntity.class);
        verify(checkListRepository).save(captor.capture());
        AddCheckListEntity shopping = captor.getValue();
        ArgumentCaptor<AddItemsEntity> captor2 =
                ArgumentCaptor.forClass(AddItemsEntity.class);
        verify(itemRepository, times(items.size())).save(captor2.capture());
        List<AddItemsEntity> fruit = captor2.getAllValues();
        resultCheck(shopping,fruit,"買い物",LocalDate.of(2026,1,1),List.of("りんご","みかん"),List.of(true,false));
    }
    @Test
    void 項目名が５０文字を超えていた場合(){
        List<AddItemsDTO> items = new ArrayList<>();
        createItemsDTO(items,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",true);
        createItemsDTO(items,"みかん",false);
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"買い物",items,"2026/01/01");
        AddUserEntity user = createUserEntity("abcdefg","qwerty");
        when(userRepository.findByUserName("abcdefg"))
                .thenReturn(user);
        assertThrows(RuntimeException.class, () -> service.addCheckList("abcdefg",dto));
    }
    @Test
    void チェックリスト名が５０文字を超えていた場合(){
        List<AddItemsDTO> items = new ArrayList<>();
        createItemsDTO(items,"りんご",true);
        createItemsDTO(items,"みかん",false);
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",items,"2026/01/01");
        AddUserEntity user = createUserEntity("abcdefg","qwerty");
        when(userRepository.findByUserName("abcdefg"))
                .thenReturn(user);
        assertThrows(RuntimeException.class, () -> service.addCheckList("abcdefg",dto));
    }
    private void createDTO(AddCheckListDTO dto, String checkListName, List<AddItemsDTO> items,String createdAt){
        dto.setCheckListName(checkListName);
        dto.setItems(items);
        dto.setCreatedAt(createdAt);
    }
    private void createItemsDTO(List<AddItemsDTO> dto,String itemName,boolean isChecked){
        AddItemsDTO item = new AddItemsDTO();
        item.setItemName(itemName);
        item.setIsChecked(isChecked);
        dto.add(item);
    }
    private AddUserEntity createUserEntity(String userName, String password){
        AddUserEntity user = new AddUserEntity();
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }
    private void resultCheck(AddCheckListEntity checkList,List<AddItemsEntity> items,String checkListName,LocalDate createdAt,List<String> itemName,List<Boolean> isChecked){
        assertEquals(checkListName,checkList.getCheckListName());
        assertEquals(createdAt,checkList.getCreatedAt());
        for(int i = 0;i<items.size();i++){
            assertEquals(itemName.get(i),items.get(i).getItemName());
            assertEquals(isChecked.get(i),items.get(i).getIsChecked());
        }
    }
}
