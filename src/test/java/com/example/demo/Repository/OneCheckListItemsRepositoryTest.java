package com.example.demo.Repository;

import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OneCheckListItemsRepositoryTest {
    @Autowired
    OneCheckListRepository repository;
    @Autowired
    OneCheckListItemsRepository iRepository;
    @Test
    void deleteItemsのテスト(){
        OneCheckListCheckListsEntity checkList = new OneCheckListCheckListsEntity();
        saveCheckListsEntity(checkList,"買い物",LocalDate.of(2026,1,1));
        OneCheckListItemsEntity items = new OneCheckListItemsEntity();
        saveItemsEntity(items,"りんご",true,checkList);
        OneCheckListItemsEntity items2 = new OneCheckListItemsEntity();
        saveItemsEntity(items2,"みかん",false,checkList);
        OneCheckListCheckListsEntity checkList2 = new OneCheckListCheckListsEntity();
        saveCheckListsEntity(checkList2,"勉強",LocalDate.of(2026,1,2));
        OneCheckListItemsEntity items3 = new OneCheckListItemsEntity();
        saveItemsEntity(items3,"数学",true,checkList2);
        OneCheckListItemsEntity items4 = new OneCheckListItemsEntity();
        saveItemsEntity(items4,"国語",false,checkList2);
        beforeCheck(items);
        beforeCheck(items2);
        Integer result = iRepository.deleteItems(checkList.getCheckListsId());
        assertEquals(2,result);
        afterNoneCheck(items);
        afterNoneCheck(items2);
        afterExistCheck(items3);
        afterExistCheck(items4);
    }
    private void saveCheckListsEntity(OneCheckListCheckListsEntity checkList,String checkListsName,LocalDate createdAt){
        checkList.setCheckListsName(checkListsName);
        checkList.setCreatedAt(createdAt);
        repository.save(checkList);
    }
    private void saveItemsEntity(OneCheckListItemsEntity items,String itemName,boolean isChecked,OneCheckListCheckListsEntity checkList){
        items.setItemName(itemName);
        items.setIsChecked(isChecked);
        items.setCheckListsEntity(checkList);
        iRepository.save(items);
    }
    private void beforeCheck(OneCheckListItemsEntity items){
        Optional<OneCheckListItemsEntity> before =
                iRepository.findById(items.getItemId());
        assertTrue(before.isPresent());
    }
    private void afterNoneCheck(OneCheckListItemsEntity items){
        Optional<OneCheckListItemsEntity> after =
                iRepository.findById(items.getItemId());
        assertFalse(after.isPresent());

    }
    private void afterExistCheck(OneCheckListItemsEntity items){
        Optional<OneCheckListItemsEntity> after =
                iRepository.findById(items.getItemId());
        assertTrue(after.isPresent());

    }
}
