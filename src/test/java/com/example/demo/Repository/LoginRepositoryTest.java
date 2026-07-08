package com.example.demo.Repository;

import com.example.demo.Entity.LoginEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LoginRepositoryTest {
    @Autowired
    LoginRepository repository;
    @Test
    void repositoryTest(){}
    LoginEntity user = new LoginEntity();
    user.setUserName("abcdefg");
    user.setPassword("qwertyuiop");
    repository.save(user);
}
