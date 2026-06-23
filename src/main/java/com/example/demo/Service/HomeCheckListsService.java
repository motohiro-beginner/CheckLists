package com.example.demo.Service;

import com.example.demo.DTO.HomeCheckListsDTO;
import com.example.demo.DTO.HomeCheckListsViewDTO;
import com.example.demo.Repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeCheckListsService {
    @Autowired
    private HomeRepository repository;
    public List<HomeCheckListsViewDTO> findAllCheckLists(String userName, LocalDateTime createdAt){
        List<HomeCheckListsDTO> checkLists = repository.findAllCheckLists(userName,createdAt);
        List<HomeCheckListsViewDTO> viewCheckLists = new ArrayList<>();
        String checkListsName;
        List<String> itemNames = new ArrayList<>();
        List<Boolean> isChecked = new ArrayList<>();
        String createdAtView;
        String prev = null;
        int checkListsNumber = 0;
        for(int i = 0;i<checkLists.size();i++){
            itemNames.add(checkLists.get(i).getItemNames());
            isChecked.add(checkLists.get(i).getIsChecked());
            if((checkLists.get(i).getCheckListsName().equals(prev)) && (prev != null) ){

                checkListsNumber++;
            }
            //別のチェックリストの行に切り替わったらviewCheckListsの格納する位置を次の位置に変える。
        }
    }
}
