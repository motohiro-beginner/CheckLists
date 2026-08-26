package com.example.demo.Service;

import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteCheckListServiceTest {
    @Mock
    OneCheckListRepository repository;
    @Mock
    OneCheckListItemsRepository iRepository;
    @InjectMocks
    DeleteCheckListService service;
    @Test
    void 問題なく削除できた場合(){
        service.deleteCheckList("1");
        verify(iRepository).deleteItems(1);
        verify(repository).deleteByCheckListsId(1);
    }
    @Test
    void 例外が発生した場合(){
        /*doThrow(new DataAccessException("DBエラー"){})
                .when(iRepository).deleteItems(1);*/
        doThrow(new DataAccessException("DBエラー"){})
                .when(repository).deleteByCheckListsId(1);
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.deleteCheckList("1")
        );
        assertEquals("削除処理に失敗しました。",exception.getMessage());
    }
}
