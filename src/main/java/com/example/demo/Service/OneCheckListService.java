package com.example.demo.Service;

import com.example.demo.DTO.OneCheckListViewDTO;
import com.example.demo.Repository.OneCheckListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneCheckListService {
    private OneCheckListRepository repository;
    public OneCheckListService(OneCheckListRepository repository){
        this.repository =repository;
    }
    public List<OneCheckListViewDTO> findOneCheckList(String checkListId){

    }
}
