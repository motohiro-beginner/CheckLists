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

import static org.junit.jupiter.api.Assertions.*;

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
        /*テストデータ1
        //実際にrepositoryのメソッドを呼び出して、テストする。
        Integer result = repository.existsByUserNameAndCreatedAt("abcdefg",nowDate);
        assertTrue(result>0);
        List<HomeCheckListsDTO> dto = repository.findAllCheckLists("abcdefg",nowDate);
        assertEquals(checkLists1.getCheckListsId(),dto.get(0).getCheckListsId());
        assertEquals("買い物",dto.get(0).getCheckListsName());
        assertEquals(items1.getItemId(),dto.get(0).getItemId());
        assertEquals("リンゴ",dto.get(0).getItemNames());
        assertEquals(false,dto.get(0).getIsChecked());
        assertEquals(nowDate,dto.get(0).getCreatedAt());
        assertEquals(checkLists2.getCheckListsId(),dto.get(1).getCheckListsId());
        assertEquals("勉強",dto.get(1).getCheckListsName());
        assertEquals(items1.getItemId(),dto.get(0).getItemId());
        assertEquals("国語",dto.get(1).getItemNames());
        assertEquals(true,dto.get(1).getIsChecked());
        assertEquals(dto.get(1).getCreatedAt(),nowDate);
         */
        Integer result = repository.existsByUserNameAndCreatedAt("aaaaa",nowDate);
        assertTrue(result == 0);

    }
}
