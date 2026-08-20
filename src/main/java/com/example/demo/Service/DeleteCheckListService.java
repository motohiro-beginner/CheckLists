package com.example.demo.Service;

import com.example.demo.Repository.OneCheckListRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCheckListService {
    private OneCheckListRepository repository;
    public DeleteCheckListService(OneCheckListRepository repository){
        this.repository = repository;
    }
    //deleteCheckListはチェックリストIDが一致するチェックリストを削除するためのメソッドである。
    public void deleteCheckList(String checkListsId){
        Integer id = Integer.parseInt(checkListsId);

    }
}
