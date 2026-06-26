package com.example.demo.DTO;

import  com.example.demo.DTO.HomeItemsViewDTO;
import java.time.LocalDateTime;
import java.util.List;
/*HomeCheckListsViewDTOはServiceクラスがControllerにDBで取り出した値を返すために作成したDTOである。
* このDTOをjavaScript側に返す。*/
public class HomeCheckListsViewDTO {
    private final String checkListsName;
    private final List<HomeItemsViewDTO> items;
    private final String createdAt;
    public HomeCheckListsViewDTO(String checkListsName,List<HomeItemsViewDTO> items,String createdAt){
        this.checkListsName = checkListsName;
        this.items = items;
        this.createdAt = createdAt;
    }
    public String getCheckListsName(){
        return checkListsName;
    }
    public List<HomeItemsViewDTO> getItems(){
        return items;
    }
    public String getCreatedAt(){
        return createdAt;
    }
}
