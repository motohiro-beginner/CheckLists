package com.example.demo.Repository;

import com.example.demo.DTO.HomeCheckListsDTO;
import com.example.demo.Entity.HomeCheckListsEntity;
import com.example.demo.Entity.HomeCheckListsItemsEntity;
import com.example.demo.Entity.HomeUserEntity;
import com.example.demo.Repository.HomeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class HomeRepositoryTest {
    @Autowired
    HomeRepository repository;
    @Autowired
    HomeCheckListsRepository Crepository;
    @Autowired
    HomeItemsRepository Irepository;
    @Test
    void repositoryTest(){
        //テストのためにDBにデータを入れている。
        HomeUserEntity user = new HomeUserEntity();
        user.setUserName("abcdefg");
        user.setPassword("qwertyuiop");
        repository.save(user);
        LocalDate nowDate = LocalDate.now();
        HomeCheckListsEntity checkLists1 = new HomeCheckListsEntity();
        checkLists1.setCheckListsName("買い物");
        checkLists1.setUserId(user);
        checkLists1.setCreatedAt(nowDate);
        Crepository.save(checkLists1);
        HomeCheckListsEntity checkLists2 = new HomeCheckListsEntity();
        checkLists2.setCheckListsName("勉強");
        checkLists2.setUserId(user);
        checkLists2.setCreatedAt(nowDate);
        Crepository.save(checkLists2);
        HomeCheckListsItemsEntity items1 = new HomeCheckListsItemsEntity();
        items1.setItemName("リンゴ");
        items1.setCheckList(checkLists1);
        items1.setIsChecked(false);
        Irepository.save(items1);
        HomeCheckListsItemsEntity items2 = new HomeCheckListsItemsEntity();
        items2.setItemName("国語");
        items2.setCheckList(checkLists2);
        items2.setIsChecked(true);
        Irepository.save(items2);
        //実際にrepositoryのメソッドを呼び出して、テストする。
        boolean result = repository.existsByUserNameAndCreatedAt("abcdefg",nowDate);
        assertTrue(result);
        List<HomeCheckListsDTO> dto = repository.findAllCheckLists("abcdefg",nowDate);
    }
}
