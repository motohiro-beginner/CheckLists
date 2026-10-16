package com.example.demo.AddCheckListIntegration;

import com.example.demo.Entity.AddCheckListEntity;
import com.example.demo.Entity.AddItemsEntity;
import com.example.demo.Entity.AddUserEntity;
import com.example.demo.Repository.AddCheckListRepository;
import com.example.demo.Repository.AddItemsRepository;
import com.example.demo.Repository.AddUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AddCheckListIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AddUserRepository uRepository;
    @Autowired
    private AddCheckListRepository cRepository;
    @Autowired
    private AddItemsRepository iRepository;
    @Autowired
    private EntityManager entityManager;
    //通常のチェックリストデータを追加できるかテストするメソッドである。
    @Test
    void チェックリスト追加テスト() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "買い物",
                    "year": "2026",
                    "month": "11",
                    "day": "01",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                .sessionAttr("userName","abcdefg")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
        entityManager.flush();
        entityManager.clear();
        List<AddUserEntity> users = uRepository.findAll();
        assertThat(users)
                .anySatisfy(user ->
                        assertThat(user.getUserName())
                .isEqualTo("abcdefg")
                );
        List<AddCheckListEntity> checkLists = cRepository.findAll();
        assertEquals("買い物",checkLists.get(0).getCheckListName());
        LocalDate testDate = checkLists.get(0).getCreatedAt();
        Integer year = testDate.getYear();
        assertEquals(2026,year);
        Integer month = testDate.getMonthValue();
        assertEquals(11,month);
        Integer day = testDate.getDayOfMonth();
        assertEquals(1,day);
        List<AddItemsEntity> items = iRepository.findAll();
        List<String> correctName = List.of("りんご","みかん");
        for(String name:correctName) {
            assertThat(items)
                    .anySatisfy(item ->
                            assertThat(item.getItemName())
                    .isEqualTo(name));
        }
    }
    /*禁止されていること
    * チェックリスト名の値が空白,チェックリスト名が50文字を超える。
    * 年の情報が"^[1-9]\\d{3}$"でない。　年の情報が空白である。
    * 月の情報が"^(0[1-9]|1[0-2])$"でない。 月の情報が空白である。
    * 日の情報が"^(0[1-9]|1[0-9]|2[0-9]|3[0-1])$"でない。 日の情報が空白である。
    * 年月日の日付が過去の日付である。
    * 項目名が50文字を超えている。
    * */
    @Test
    void チェックリスト名の値が空白だった場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "",
                    "year": "2026",
                    "month": "11",
                    "day": "01",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void チェックリスト名が50文字を超えていた場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "year": "2026",
                    "month": "11",
                    "day": "01",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void 年の情報が数値ではなかった場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "買い物",
                    "year": "aaa",
                    "month": "11",
                    "day": "01",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void 月の情報が数値ではなかった場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "買い物",
                    "year": "2026",
                    "month": "aaa",
                    "day": "01",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void 日の情報が数値ではなかった場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "買い物",
                    "year": "2026",
                    "month": "11",
                    "day": "aaa",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void 入力された日付が過去の日付だった場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "買い物",
                    "year": "2026",
                    "month": "01",
                    "day": "01",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void 入力した日付が実在しない日付だった場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "買い物",
                    "year": "2026",
                    "month": "11",
                    "day": "31",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "みかん"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void 項目名が50文字を超えていた場合() throws Exception{
        setUserRepository();
        String requestJson = """
                {
                    "checkListName": "買い物",
                    "year": "2026",
                    "month": "11",
                    "day": "01",
                    "items": [
                        {
                            "itemName": "りんご"
                        },
                        {
                            "itemName": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/addCheckList")
                        .sessionAttr("userName","abcdefg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void テスト用DBからデータを取得できる(){
        uRepository.findAll();
    }
    private void setUserRepository(){
        AddUserEntity userEntity = new AddUserEntity();
        userEntity.setUserName("abcdefg");
        userEntity.setPassword("qwerty");
        uRepository.save(userEntity);
    }
}
