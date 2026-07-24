package com.example.demo.DTO;

import  com.example.demo.DTO.HomeItemsViewDTO;
import java.time.LocalDateTime;
import java.util.List;
/*HomeCheckListsViewDTOはServiceクラスがControllerにDBで取り出した値を返すために作成したDTOである。
* このDTOをjavaScript側に返す。*/
public class HomeCheckListsViewDTO {
    private final Integer checkListsId;
    private final String checkListsName;
    private final List<HomeItemsViewDTO> items;
    private final String createdAt;
    public HomeCheckListsViewDTO(Integer checkListsId,String checkListsName,List<HomeItemsViewDTO> items,String createdAt){
        this.checkListsId = checkListsId;
        this.checkListsName = checkListsName;
        this.items = List.copyOf(items);
        this.createdAt = createdAt;
    }
    public Integer getCheckListsId(){
        return checkListsId;
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
