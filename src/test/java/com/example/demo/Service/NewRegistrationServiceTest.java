package com.example.demo.Service;

import com.example.demo.Repository.NewRegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewRegistrationServiceTest {
    @Mock
    NewRegistrationRepository repository;
    @InjectMocks
    NewRegistrationService service;
    @Test
    void existsByUserNameTest(){
        /*テストデータ1
        when(repository.existsByUserName("abcdefg"))
                .thenReturn(true);
        boolean result = service.existsByUserName("abcdefg");
        assertTrue(result);
         */
        /*テストデータ2
        when(repository.existsByUserName("abcdefg"))
                .thenReturn(false);
        boolean result = service.existsByUserName("abcdefg");
        assertFalse(result);
         */
    }
}
