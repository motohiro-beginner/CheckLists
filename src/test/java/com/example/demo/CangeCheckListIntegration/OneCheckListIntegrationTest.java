package com.example.demo.CangeCheckListIntegration;

import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListUserEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import com.example.demo.Repository.OneCheckListUserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OneCheckListIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OneCheckListUserRepository uRepository;
    @Autowired
    private OneCheckListRepository cRepository;
    @Autowired
    private OneCheckListItemsRepository iRepository;
    @Autowired
    private EntityManager entityManager;
    @Test
    void チェックリスト表示テスト() throws Exception{
        setUserRepository();
        mockMvc.perform(post("/showOneCheckList/{checkListsId}",1)
                .sessionAttr("userName","abcdefg")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        entityManager.flush();
        entityManager.clear();
        List<OneCheckListUserEntity> users = uRepository.findAll();
        assertThat(users)
                .anySatisfy(user ->
                        assertThat(user.getUserName())
                .isEqualTo("abcdefg")
                );
        List<OneCheckListCheckListsEntity> checkLists = cRepository.findAll();
        assertThat(checkLists)
    }
    private void setUserRepository(){
        OneCheckListUserEntity userEntity = new OneCheckListUserEntity();
        userEntity.setUserName("abcdefg");
        userEntity.setPassword("qwerty");
        uRepository.save(userEntity);
        OneCheckListCheckListsEntity checkListEntity = new OneCheckListCheckListsEntity();
        checkListEntity.setCheckListsName("買い物");
        checkListEntity.setCreatedAt(LocalDate.of(2026,11,1));

    }
}
