package com.example.demo.CangeCheckListIntegration;

import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        List<Integer> someId = setRepository();
        mvc.perform(post("/saveCheckListName")
                .sessionAttr("userName","abcdefg")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "checkListsId": "%s",
                                "checkListName": "買い物",
                                "items": [
                                    {
                                    "itemId": "%s",
                                    "itemNames": "りんご",
                                    "isChecked": true
                                    },
                                    {
                                    "itemId": "%s",
                                    "itemNames": "みかん",
                                    "isChecked": false
                                    }
                                ],
                                "newItems": [
                                    {
                                    "itemName": "いちご",
                                    "isChecked": true
                                    }
                                ],
                                "year": "2026",
                                "month": "11",
                                "day": "01"
                        }
                        """.formatted(
                                someId.get(0),
                        someId.get(1),
                        someId.get(2)
                )))
                .andExpect(status().isOk());
        List<OneCheckListCheckListsEntity> checkLists = cRepository.findAll();
        assertThat(checkLists)
                .anySatisfy(checkList -> {
                    assertThat(checkList.getCheckListsId())
                            .isEqualTo(someId.get(0));
                    assertThat(checkList.getCheckListsName())
                            .isEqualTo("買い物");
                    assertThat(checkList.getCreatedAt())
                            .isEqualTo(LocalDate.of(2026,11,1));
                });
        List<OneCheckListItemsEntity> items = iRepository.findAll();
        assertThat(items)
                .anySatisfy(item -> {
                    assertThat(item.getItemId())
                            .isEqualTo(someId.get(1));
                    assertThat(item.getItemName())
                            .isEqualTo("りんご");
                    assertThat(item.getIsChecked())
                            .isEqualTo(true);
                });
        assertThat(items)
                .anySatisfy(item -> {
                    assertThat(item.getItemId())
                            .isEqualTo(someId.get(2));
                    assertThat(item.getItemName())
                            .isEqualTo("みかん");
                    assertThat(item.getIsChecked())
                            .isEqualTo(false);
                });
    }
    @Test
    void ユーザー名が消失していた場合() throws Exception{
        List<Integer> someId = setRepository();
        mvc.perform(post("/saveCheckListName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "checkListsId": "%s",
                                "checkListName": "買い物",
                                "items": [
                                    {
                                    "itemId": "%s",
                                    "itemNames": "りんご",
                                    "isChecked": true
                                    },
                                    {
                                    "itemId": "%s",
                                    "itemNames": "みかん",
                                    "isChecked": false
                                    }
                                ],
                                "newItems": [
                                    {
                                    "itemName": "いちご",
                                    "isChecked": true
                                    }
                                ],
                                "year": "2026",
                                "month": "11",
                                "day": "01"
                        }
                        """.formatted(
                                someId.get(0),
                                someId.get(1),
                                someId.get(2)
                        )))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("ユーザー名が消失したため、ログイン画面に戻りました。"));
    }
    @Test
    void 該当するチェックリストがなかった場合() throws Exception{
        List<Integer> someId = setRepository();
        mvc.perform(post("/saveCheckListName")
                        .sessionAttr("userName","abcdefgh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "checkListsId": "%s",
                                "checkListName": "買い物",
                                "items": [
                                    {
                                    "itemId": "%s",
                                    "itemNames": "りんご",
                                    "isChecked": true
                                    },
                                    {
                                    "itemId": "%s",
                                    "itemNames": "みかん",
                                    "isChecked": false
                                    }
                                ],
                                "newItems": [
                                    {
                                    "itemName": "いちご",
                                    "isChecked": true
                                    }
                                ],
                                "year": "2026",
                                "month": "11",
                                "day": "01"
                        }
                        """.formatted(
                                someId.get(0),
                                someId.get(1),
                                someId.get(2)
                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("参照するチェックリストにアクセスする権限がありません。チェックリストIDを改ざんしましたね?"));
    }
    @Test
    void 入力された日付が過去の日付だった場合() throws Exception{
        List<Integer> someId = setRepository();
        mvc.perform(post("/saveCheckListName")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "checkListsId": "%s",
                                "checkListName": "買い物",
                                "items": [
                                    {
                                    "itemId": "%s",
                                    "itemNames": "りんご",
                                    "isChecked": true
                                    },
                                    {
                                    "itemId": "%s",
                                    "itemNames": "みかん",
                                    "isChecked": false
                                    }
                                ],
                                "newItems": [
                                    {
                                    "itemName": "いちご",
                                    "isChecked": true
                                    }
                                ],
                                "year": "2026",
                                "month": "01",
                                "day": "01"
                        }
                        """.formatted(
                                someId.get(0),
                                someId.get(1),
                                someId.get(2)
                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("チェックリストに入力する日付に過去の日付を入力しないでください。"));
    }
    @Test
    void 更新後のチェックリストに項目がなかった場合() throws Exception{
        List<Integer> someId = setRepository();
        mvc.perform(post("/saveCheckListName")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "checkListsId": "%s",
                                "checkListName": "買い物",
                                "items": [],
                                "newItems": [],
                                "year": "2026",
                                "month": "11",
                                "day": "01"
                        }
                        """.formatted(
                                someId.get(0)
                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("更新後のチェックリストに項目がありません。"));
    }
    private List<Integer> setRepository(){
        OneCheckListUserEntity user = new OneCheckListUserEntity();
        user.setUserName("abcdefg");
        user.setPassword("qwerty");
        uRepository.save(user);
        OneCheckListCheckListsEntity checkListEntity = new OneCheckListCheckListsEntity();
        checkListEntity.setCheckListsName("買い物");
        checkListEntity.setCreatedAt(LocalDate.of(2026,11,1));
        checkListEntity.setUser(user);
        OneCheckListCheckListsEntity savedCheckListEntity = cRepository.save(checkListEntity);
        List<Integer> someId = new ArrayList<>();
        someId.add(savedCheckListEntity.getCheckListsId());
        OneCheckListItemsEntity itemsEntity = new OneCheckListItemsEntity();
        itemsEntity.setItemName("りんご");
        itemsEntity.setIsChecked(false);
        itemsEntity.setCheckListsEntity(checkListEntity);
        OneCheckListItemsEntity savedItemsEntity = iRepository.save(itemsEntity);
        OneCheckListItemsEntity itemsEntity2 = new OneCheckListItemsEntity();
        itemsEntity2.setItemName("みかん");
        itemsEntity2.setIsChecked(true);
        itemsEntity2.setCheckListsEntity(checkListEntity);
        OneCheckListItemsEntity savedItemsEntity2 = iRepository.save(itemsEntity2);
        someId.add(savedItemsEntity.getItemId());
        someId.add(savedItemsEntity2.getItemId());
        return someId;
        //戻り値をチェックするのにcheckListsIdとitemIdが必要になる。
    }
}
