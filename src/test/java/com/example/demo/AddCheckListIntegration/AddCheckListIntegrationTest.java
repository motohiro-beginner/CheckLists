package com.example.demo.AddCheckListIntegration;

import com.example.demo.Repository.AddCheckListRepository;
import com.example.demo.Repository.AddItemsRepository;
import com.example.demo.Repository.AddUserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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
    private AddUserRepository repository;
    @Autowired
    private AddCheckListRepository cRepository;
    @Autowired
    private AddItemsRepository iRepository;
    @Test
    void チェックリスト追加テスト() throws Exception{
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
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }
    @Test
    void テスト用DBからデータを取得できる(){
        repository.findAll();
    }
}
