package com.example.demo.Service;

import com.example.demo.DTO.HomeCheckListsDTO;
import com.example.demo.DTO.HomeCheckListsViewDTO;
import com.example.demo.DTO.HomeItemsViewDTO;
import com.example.demo.Repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        List<HomeItemsViewDTO> items = new ArrayList<>();
        String checkListsName = "";
        String createdAtView = "";
        //checkListsName,items,createdAtViewはHomeCheckListsViewDTOに該当する値を格納するために用意する変数である。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String prev = null;
        for(HomeCheckListsDTO dto:checkLists){
            if(prev == null){
                //もし最初の行の部分ならこの部分を実行する。
                checkListsName = dto.getCheckListsName();
                createdAtView = dto.getCreatedAt().format(formatter);
            }else if(!(dto.getCheckListsName().equals(prev))){
                viewCheckLists.add(new HomeCheckListsViewDTO(checkListsName,items,createdAtView));
                items = new ArrayList<>();
                checkListsName = dto.getCheckListsName();
                createdAtView = dto.getCreatedAt().format(formatter);
                /*checkLists名が切り替わったら別のチェックリストの行に変わったと判断し、viewCheckListsに
                * checkListsName,itemNames,isChecked,createdAtViewを格納し、次のチェックリスト用に初期化する。*/
            }
            items.add(new HomeItemsViewDTO(dto.getItemNames(),dto.getIsChecked()));
            prev = dto.getCheckListsName();
        }
        items.add(new HomeItemsViewDTO(checkLists.get(checkLists.size()-1).getItemNames(),checkLists.get(checkLists.size()-1).getIsChecked()));
        viewCheckLists.add(new HomeCheckListsViewDTO(checkListsName,items,createdAtView));
        //最後のチェックリストをviewCheckListsに格納する。
        return viewCheckLists;
    }
}
