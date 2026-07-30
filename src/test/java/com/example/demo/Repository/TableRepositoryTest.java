package com.example.demo.Repository;

import com.example.demo.DTO.TableDTO;
import com.example.demo.Entity.TableCheckListsEntity;
import com.example.demo.Entity.TableCheckListsItemsEntity;
import com.example.demo.Entity.TableUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TableRepositoryTest {
    @Autowired
    TableRepository repository;
    @Autowired
    TableCheckListsRepository Crepository;
    @Autowired
    TableItemsRepository Irepository;
    @Test
    void repositoryTest(){
        TableUserEntity user = new TableUserEntity();
        user.setUserName("abcdefg");
        user.setPassword("qwerty12");
        repository.save(user);
        TableCheckListsEntity checkLists = new TableCheckListsEntity();
        checkLists.setCheckListsId(1);
        checkLists.setCheckListsName("買い物");
        checkLists.setUserEntity(user);
        LocalDate date = LocalDate.of(2026,1,1);
        checkLists.setCreatedAt(date);
        Crepository.save(checkLists);
        TableCheckListsEntity checkLists2 = new TableCheckListsEntity();
        checkLists2.setCheckListsId(2);
        checkLists2.setCheckListsName("勉強");
        checkLists2.setUserEntity(user);
        LocalDate date2 = LocalDate.of(2026,1,2);
        checkLists2.setCreatedAt(date2);
        Crepository.save(checkLists2);
        TableCheckListsItemsEntity items = new TableCheckListsItemsEntity();
        items.setItemId(1);
        items.setItemName("リンゴ");
        items.setIsChecked(true);
        items.setCheckListsEntity(checkLists);
        Irepository.save(items);
        TableCheckListsItemsEntity items2 = new TableCheckListsItemsEntity();
        items2.setItemId(2);
        items2.setItemName("数学");
        items2.setIsChecked(false);
        items2.setCheckListsEntity(checkLists2);
        Irepository.save(items2);

        Integer result = repository.existsByUserCheckLists("abcdefg");
        assertTrue(result>0);
        List<TableDTO> dto = repository.findTableAllCheckLists("abcdefg");
        assertEquals("買い物",dto.get(0).getCheckListsName());
        assertEquals("リンゴ",dto.get(0).getItemNames());
        assertTrue(dto.get(0).getIsChecked());
        assertEquals(date,dto.get(0).getCreatedAt());
        assertEquals("勉強",dto.get(1).getCheckListsName());
        assertEquals("数学",dto.get(1).getItemNames());
        assertFalse(dto.get(1).getIsChecked());
        assertEquals(date2,dto.get(1).getCreatedAt());


        /*
        Integer result = repository.existsByUserCheckLists("aaaaa");
        assertFalse(result>0);
         */
    }
    @Test
    void findSearchedCheckListsのテスト(){
        TableUserEntity abcdefg = saveUserEntity("abcdefg","qwerty");
        LocalDate date = LocalDate.of(2026,1,1);
        LocalDate date2 = LocalDate.of(2026,1,2);
        TableCheckListsEntity shopping = saveCheckListsEntity(abcdefg,"買い物",date);
        TableCheckListsEntity study = saveCheckListsEntity(abcdefg,"勉強",date2);
        TableCheckListsItemsEntity fruit = saveItemsEntity(shopping,"リンゴ",true);
        TableCheckListsItemsEntity subject = saveItemsEntity(study,"数学",false);
        List<TableDTO> result = repository.findSearchedCheckLists()
    }
    private TableUserEntity saveUserEntity(String userName,String password){
        TableUserEntity user = new TableUserEntity();
        user.setUserName(userName);
        user.setPassword(password);
        return repository.save(user);
    }
    private TableCheckListsEntity saveCheckListsEntity(TableUserEntity user,String checkListsName,LocalDate createdAt){
        TableCheckListsEntity list = new TableCheckListsEntity();
        list.setCheckListsName(checkListsName);
        list.setCreatedAt(createdAt);
        list.setUserEntity(user);
        return Crepository.save(list);
    }
    private TableCheckListsItemsEntity saveItemsEntity(TableCheckListsEntity list,String itemName,boolean isChecked){
        TableCheckListsItemsEntity items = new TableCheckListsItemsEntity();
        items.setItemName(itemName);
        items.setIsChecked(isChecked);
        items.setCheckListsEntity(list);
        return Irepository.save(items);
    }
}
