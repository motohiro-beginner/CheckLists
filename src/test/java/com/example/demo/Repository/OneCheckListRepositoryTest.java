package com.example.demo.Repository;

import com.example.demo.DTO.OneCheckListDTO;
import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OneCheckListRepositoryTest {
    @Autowired
    OneCheckListRepository repository;
    @Autowired
    OneCheckListItemsRepository iRepository;
    @Test
    void findOneCheckListのテスト(){
        OneCheckListCheckListsEntity checkList = createCheckListsEntity("買い物",LocalDate.of(2026,1,1));
        repository.save(checkList);
        iRepository.save(createItemsEntity(checkList,"りんご",true));
        iRepository.save(createItemsEntity(checkList,"みかん",false));
        OneCheckListCheckListsEntity checkList2 = createCheckListsEntity("勉強",LocalDate.of(2026,1,2));
        repository.save(checkList2);
        iRepository.save(createItemsEntity(checkList2,"国語",true));
        iRepository.save(createItemsEntity(checkList2,"数学",false));
        List<OneCheckListDTO> result = repository.findOneCheckList(1);
        assertCheck(result.get(0),1,"買い物","りんご",true,LocalDate.of(2026,1,1));
        assertCheck(result.get(1),1,"買い物","みかん",false,LocalDate.of(2026,1,1));
    }
    @Test
    void findOneCheckListで戻り値がnullの場合(){
        OneCheckListCheckListsEntity checkList = createCheckListsEntity("買い物",LocalDate.of(2026,1,1));
        repository.save(checkList);
        iRepository.save(createItemsEntity(checkList,"りんご",true));
        iRepository.save(createItemsEntity(checkList,"みかん",false));
        OneCheckListCheckListsEntity checkList2 = createCheckListsEntity("勉強",LocalDate.of(2026,1,2));
        repository.save(checkList2);
        iRepository.save(createItemsEntity(checkList2,"国語",true));
        iRepository.save(createItemsEntity(checkList2,"数学",false));
        List<OneCheckListDTO> result = repository.findOneCheckList(5);
        assertEquals(List.of(),result);
    }
    private OneCheckListCheckListsEntity createCheckListsEntity(String checkListsName, LocalDate createdAt){
        OneCheckListCheckListsEntity checkList = new OneCheckListCheckListsEntity();
        checkList.setCheckListsName(checkListsName);
        checkList.setCreatedAt(createdAt);
        return checkList;
    }
    private OneCheckListItemsEntity createItemsEntity(OneCheckListCheckListsEntity checkList,String itemName,boolean isChecked){
        OneCheckListItemsEntity items = new OneCheckListItemsEntity();
        items.setCheckListsEntity(checkList);
        items.setItemName(itemName);
        items.setIsChecked(isChecked);
        return items;
    }
    private void assertCheck(OneCheckListDTO result,Integer checkListId,String checkListName,String itemName,boolean isChecked,LocalDate createdAt){
        assertEquals(checkListId,result.getCheckListId());
        assertEquals(checkListName,result.getCheckListName());
        assertEquals(itemName,result.getItemName());
        assertEquals(isChecked,result.getIsChecked());
        assertEquals(createdAt,result.getCreatedAt());
    }
}
