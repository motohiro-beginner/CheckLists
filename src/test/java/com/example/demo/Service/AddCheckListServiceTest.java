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
        createItemsDTO(items,"りんご");
        createItemsDTO(items,"みかん");
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"買い物","2026","10","1",items);
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
        resultCheck(shopping,fruit,"買い物",LocalDate.of(2026,10,1),List.of("りんご","みかん"));
    }
    @Test
    void 項目名が５０文字を超えていた場合(){
        List<AddItemsDTO> items = new ArrayList<>();
        createItemsDTO(items,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        createItemsDTO(items,"みかん");
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"買い物","2026","10","01",items);
        AddUserEntity user = createUserEntity("abcdefg","qwerty");
        when(userRepository.findByUserName("abcdefg"))
                .thenReturn(user);
        assertThrows(RuntimeException.class, () -> service.addCheckList("abcdefg",dto));
    }
    @Test
    void チェックリスト名が５０文字を超えていた場合(){
        List<AddItemsDTO> items = new ArrayList<>();
        createItemsDTO(items,"りんご");
        createItemsDTO(items,"みかん");
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","2026","10","1",items);
        AddUserEntity user = createUserEntity("abcdefg","qwerty");
        when(userRepository.findByUserName("abcdefg"))
                .thenReturn(user);
        assertThrows(RuntimeException.class, () -> service.addCheckList("abcdefg",dto));
    }
    @Test
    void 入力された日付が過去の日付だった場合(){
        List<AddItemsDTO> items = new ArrayList<>();
        createItemsDTO(items,"りんご");
        createItemsDTO(items,"みかん");
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"買い物","2026","1","1",items);
        AddUserEntity user = createUserEntity("abcdefg","qwerty");
        when(userRepository.findByUserName("abcdefg"))
                .thenReturn(user);
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.addCheckList("abcdefg",dto));
        errorCheck(exception,"チェックリストに入力する日付に過去の日付を入力しないでください。");
    }
    @Test
    void 入力された日付が実在しない日付だった場合(){
        List<AddItemsDTO> items = new ArrayList<>();
        createItemsDTO(items,"りんご");
        createItemsDTO(items,"みかん");
        AddCheckListDTO dto = new AddCheckListDTO();
        createDTO(dto,"買い物","2026","9","31",items);
        AddUserEntity user = createUserEntity("abcdefg","qwerty");
        when(userRepository.findByUserName("abcdefg"))
                .thenReturn(user);
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.addCheckList("abcdefg",dto));
        errorCheck(exception,"実在しない日付が入力されています。");
    }
    private void createDTO(AddCheckListDTO dto, String checkListName,String year,String month,String day, List<AddItemsDTO> items){
        dto.setCheckListName(checkListName);
        dto.setYear(year);
        dto.setMonth(month);
        dto.setDay(day);
        dto.setItems(items);
    }
    private void createItemsDTO(List<AddItemsDTO> dto,String itemName){
        AddItemsDTO item = new AddItemsDTO();
        item.setItemName(itemName);
        dto.add(item);
    }
    private AddUserEntity createUserEntity(String userName, String password){
        AddUserEntity user = new AddUserEntity();
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }
    private void resultCheck(AddCheckListEntity checkList,List<AddItemsEntity> items,String checkListName,LocalDate createdAt,List<String> itemName){
        assertEquals(checkListName,checkList.getCheckListName());
        assertEquals(createdAt,checkList.getCreatedAt());
        for(int i = 0;i<items.size();i++){
            assertEquals(itemName.get(i),items.get(i).getItemName());
            assertEquals(false,items.get(i).getIsChecked());
        }
    }
    private void errorCheck(RuntimeException exception,String errorMessage){
        assertEquals(exception.getMessage(),errorMessage);
    }
}
