package com.example.demo.Service;

import com.example.demo.Entity.OneCheckListCheckListsEntity;
import com.example.demo.Entity.OneCheckListItemsEntity;
import com.example.demo.Repository.OneCheckListItemsRepository;
import com.example.demo.Repository.OneCheckListRepository;
import jakarta.transaction.Transactional;
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
        Integer id = Integer.parseInt(checkListsId);
        iRepository.deleteItems(id);
        repository.deleteByCheckListsId(id);
    }
}
