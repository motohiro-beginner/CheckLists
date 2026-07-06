package com.example.demo.Service;

import com.example.demo.Repository.LoginRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @Mock//偽物のRepositoryを作成
    LoginRepository repository;
    @InjectMocks//偽物のRepositoryを注入
    LoginService service;
    @Test
    void existByUserNameAndPasswordTest(){
        when(repository.existsByUserNameAndPassword("abcdefg","qwerty"))
                .thenReturn(false);
        boolean result = service.existByUserNameAndPassword("abcdefg","qwerty");
        assertFalse(result);
    }
}
