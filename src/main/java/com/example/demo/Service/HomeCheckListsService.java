package com.example.demo.Service;

import com.example.demo.DTO.HomeCheckListsDTO;
import com.example.demo.DTO.HomeCheckListsViewDTO;
import com.example.demo.Repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeCheckListsService {
    @Autowired
    private HomeRepository repository;
    /*HomeCheckListsDTOの中にある変数は
    private final String checkListsName;
    private final String itemNames;
    private final boolean isChecked;
    private final LocalDateTime createdAt;
    である。
    HomeCheckListsViewDTOの中にある変数は
    private final String checkListsName;
    private final List<String> itemNames;
    private final List<Boolean> isChecked;
    private final String createdAt;
    である。
    findAllCheckListsはList<HomeCheckListsDTO>からList<HomeCheckListViewDTO>に変換するメソッドである。
    なお、HomeCheckListsDTOはDBの行の値が格納されているのに対して、HomeCheckListViewDTOは
    各CheckListsごとの値が格納されている。*/
    public List<HomeCheckListsViewDTO> findAllCheckLists(String userName, LocalDateTime createdAt){
        List<HomeCheckListsDTO> checkLists = repository.findAllCheckLists(userName,createdAt);
        List<HomeCheckListsViewDTO> viewCheckLists = new ArrayList<>();
        StringBuilder checkListsName = new StringBuilder();
        List<String> itemNames = new ArrayList<>();
        List<Boolean> isChecked = new ArrayList<>();
        StringBuilder createdAtView = new StringBuilder();
        //checkListsName,itemNames,isChecked,createdAtViewはHomeCheckListsViewDTOに該当する値を格納するために用意する変数である。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String prev = null;
        for(int i = 0;i<checkLists.size();i++){
            if(prev == null){
                //もし最初の行の部分ならこの部分を実行する。
                checkListsName.append(checkLists.get(i).getCheckListsName());
                createdAtView.append(checkLists.get(i).getCreatedAt().format(formatter));
            }else if(!(checkLists.get(i).getCheckListsName().equals(prev))){
                viewCheckLists.add(new HomeCheckListsViewDTO(checkListsName.toString(),itemNames,isChecked,createdAtView.toString()));
                checkListsName.setLength(0);
                itemNames.clear();
                isChecked.clear();
                createdAtView.setLength(0);
                checkListsName.append(checkLists.get(i).getCheckListsName());
                createdAtView.append(checkLists.get(i).getCreatedAt().format(formatter));
                /*checkLists名が切り替わったら別のチェックリストの行に変わったと判断し、viewCheckListsに
                * checkListsName,itemNames,isChecked,createdAtViewを格納し、次のチェックリスト用に初期化する。*/
            }else if(i-1<checkLists.size()){
                itemNames.add(checkLists.get(i).getItemNames());
                isChecked.add(checkLists.get(i).getIsChecked());
                viewCheckLists.add(new HomeCheckListsViewDTO(checkListsName.toString(),itemNames,isChecked,createdAtView.toString()));
                //最後の行だった場合viewCheckListsに格納する。
            }
            itemNames.add(checkLists.get(i).getItemNames());
            isChecked.add(checkLists.get(i).getIsChecked());
            prev = checkLists.get(i).getCheckListsName();
        }
        return viewCheckLists;
    }
}
