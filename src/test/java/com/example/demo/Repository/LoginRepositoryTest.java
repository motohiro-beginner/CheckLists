package com.example.demo.Repository;

import com.example.demo.Entity.LoginEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class LoginRepositoryTest {
    @Autowired
    LoginRepository repository;
    @Test
    void repositoryTest() {
        //テストのために組み込みDBにデータを入れている。
        LoginEntity user = new LoginEntity();
        user.setUserName("abcdefg");
        user.setPassword("qwertyuiop");
        repository.save(user);
        //実際にメソッドを実行して、結果を確かめる。
        //boolean result = repository.existsByUserNameAndPassword("abcdefg","qwertyuiop");
        //assertTrue(result);
        boolean result = repository.existsByUserNameAndPassword("abc","qwertyuiop");
        assertFalse(result);
    }
}
