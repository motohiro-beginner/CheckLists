package com.example.demo.Repository;

import com.example.demo.DTO.OneCheckListDTO;
import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class OneCheckListRepositoryTest {
    @Autowired
    OneCheckListRepository repository;
    @Autowired
    OneCheckListItemsRepository iRepository;
    @Test
    void findOneCheckListのテスト(){
        repository.save(createCheckListsEntity(1,"買い物",LocalDate.of(2026,1,1)));
        iRepository.save(createItemsEntity(1,"りんご",true));
        iRepository.save(createItemsEntity(2,"みかん",false));
        List<OneCheckListDTO> result = repository.findOneCheckList(1);

    }
    private OneCheckListCheckListsEntity createCheckListsEntity(Integer checkListsId, String checkListsName, LocalDate createdAt){
        OneCheckListCheckListsEntity checkList = new OneCheckListCheckListsEntity();
        checkList.setCheckListsId(checkListsId);
        checkList.setCheckListsName(checkListsName);
        checkList.setCreatedAt(createdAt);
        return checkList;
    }
    private OneCheckListItemsEntity createItemsEntity(Integer itemId,String itemName,boolean isChecked){
        OneCheckListItemsEntity items = new OneCheckListItemsEntity();
        items.setItemId(itemId);
        items.setItemName(itemName);
        items.setIsChecked(isChecked);
        return items;
    }
    private void assertCheck(Integer checkListId,String checkListName,Integer itemId,String itemName,boolean isChecked,LocalDate createdAt){

    }
}
