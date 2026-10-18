package com.example.demo.CangeCheckListIntegration;

import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import com.example.demo.Entity.OneCheckListUserEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import com.example.demo.Repository.OneCheckListUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
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
        List<Integer> someId = setUserRepository();
        MvcResult result = mockMvc.perform(post("/showOneCheckList/{checkListsId}",1)
                .sessionAttr("userName","abcdefg")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        entityManager.flush();
        entityManager.clear();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals(
                """
                        {
                            "checkListId": "%s",
                            "checkListName": "買い物",
                            "items": [
                                {
                                "itemId": "%s",
                                "itemName": "りんご",
                                "isChecked": true
                                },
                                {
                                "itemId": "%s",
                                "itemName": "みかん",
                                "isChecked": false
                                }
                            ],
                            "createdAt": "2026/11/01"
                        }
                        """.formatted(
                                someId.get(0),
                                someId.get(1),
                                someId.get(2)
                        ),
                response,
                JSONCompareMode.LENIENT
        );
    }
    @Test
    void セッション情報がなかった場合() throws Exception{
        List<Integer> someId = setUserRepository();
        mockMvc.perform(post("/showOneCheckList/{checkListsId}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ユーザー名が消失したため、ログイン画面に戻りました。"));
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void 該当するチェックリストがなかった場合() throws Exception{
        List<Integer> someId = setUserRepository();
        mockMvc.perform(post("/showOneCheckList/{checkListsId}",2)
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("参照するチェックリストにアクセスする権限がありません。チェックリストIDを改ざんしましたね?"));
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void テストDBの中身を削除(){
        uRepository.deleteAll();
    }
    //setUserRepositoryは事前にテストDBに必要な値を入れておくために用意するメソッドである。
    private List<Integer> setUserRepository(){
        OneCheckListUserEntity userEntity = new OneCheckListUserEntity();
        userEntity.setUserName("abcdefg");
        userEntity.setPassword("qwerty");
        uRepository.save(userEntity);
        OneCheckListCheckListsEntity checkListEntity = new OneCheckListCheckListsEntity();
        checkListEntity.setCheckListsName("買い物");
        checkListEntity.setCreatedAt(LocalDate.of(2026,11,1));
        checkListEntity.setUser(userEntity);
        OneCheckListCheckListsEntity savedCheckListEntity = cRepository.save(checkListEntity);
        List<Integer> someId = new ArrayList<>();
        someId.add(savedCheckListEntity.getCheckListsId());
        OneCheckListItemsEntity itemsEntity = new OneCheckListItemsEntity();
        itemsEntity.setItemName("りんご");
        itemsEntity.setIsChecked(true);
        itemsEntity.setCheckListsEntity(checkListEntity);
        OneCheckListItemsEntity savedItemsEntity = iRepository.save(itemsEntity);
        OneCheckListItemsEntity itemsEntity2 = new OneCheckListItemsEntity();
        itemsEntity2.setItemName("みかん");
        itemsEntity2.setIsChecked(false);
        itemsEntity2.setCheckListsEntity(checkListEntity);
        OneCheckListItemsEntity savedItemsEntity2 = iRepository.save(itemsEntity2);
        someId.add(savedItemsEntity.getItemId());
        someId.add(savedItemsEntity2.getItemId());
        return someId;
        //戻り値をチェックするのにcheckListsIdとitemIdが必要になる。
    }
}
