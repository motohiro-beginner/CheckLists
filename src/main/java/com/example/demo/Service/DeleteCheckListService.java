package com.example.demo.Service;

import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DeleteCheckListService {
    private OneCheckListRepository repository;
    private OneCheckListItemsRepository iRepository;
    public DeleteCheckListService(OneCheckListRepository repository,OneCheckListItemsRepository iRepository){
        this.repository = repository;
        this.iRepository = iRepository;
    }
    //deleteCheckListはチェックリストIDが一致するチェックリストを削除するためのメソッドである。
    @Transactional
    public void deleteCheckList(String checkListsId){
        try {
            Integer id = Integer.parseInt(checkListsId);
            iRepository.deleteItems(id);
            repository.deleteByCheckListsId(id);
        }catch(DataAccessException e){
            //Controllerに例外を伝播させるためにRuntimeExceptionを投げる。
            throw new RuntimeException("削除処理に失敗しました。");
        }
    }
}
