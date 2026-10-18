package com.example.demo.CangeCheckListIntegration;

import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListUserEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import com.example.demo.Repository.OneCheckListUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class SaveCheckListIntegrationTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private OneCheckListUserRepository uRepository;
    @Autowired
    private OneCheckListRepository cRepository;
    @Autowired
    private OneCheckListItemsRepository iRepository;
    @Autowired
    private EntityManager entityManager;
    @Test
    void 問題なく実行できた場合() throws Exception{
        setRepository();
        mvc.perform(post("/saveCheckListName")
                .sessionAttr("userName","abcdefg")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        "checkListId": 1,
                            "checkListName": "買い物",
                            "items": [
                                {
                                "itemId": 1,
                                "itemName": "りんご",
                                "isChecked": true
                                },
                                {
                                "itemId": 1,
                                "itemName": "みかん",
                                "isChecked": false
                                }
                            ],
                            "createdAt": "2026/11/01"
                        """))
                .andExpect(status().isOk());
        List<OneCheckListCheckListsEntity> checkLists = cRepository.findAll();
    }
    private void setRepository(){
        OneCheckListUserEntity user = new OneCheckListUserEntity();
        user.setUserName("abcdefg");
        user.setPassword("qwerty");
        uRepository.save(user);
    }
}
