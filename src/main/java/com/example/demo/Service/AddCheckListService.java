package com.example.demo.Service;

import com.example.demo.DTO.AddCheckListDTO;
import org.springframework.stereotype.Service;

@Service
public class AddCheckListService {
    private AddCheckListRepository repository;
    public AddCheckListService(AddCheckListRepository repository){
        this.repository = repository;
    }
    //addCheckListは追加するチェックリストの情報を受け取りDBに格納するメソッドである。
    public void addCheckList(AddCheckListDTO dto){

    }
}
