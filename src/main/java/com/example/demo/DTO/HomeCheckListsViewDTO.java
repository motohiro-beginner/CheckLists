package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.List;
/*HomeCheckListsViewDTOはServiceクラスがControllerにDBで取り出した値を返すために作成したDTOである。*/
public class HomeCheckListsViewDTO {
    private final String checkListsName;
    private final List<String> itemNames;
    private final List<Boolean> isChecked;
    private final String createdAt;
    public HomeCheckListsViewDTO(String checkListsName,List<String> itemNames,List<Boolean> isChecked,String createdAt){
        this.checkListsName = checkListsName;
        this.itemNames = itemNames;
        this.isChecked = isChecked;
        this.createdAt = createdAt;
    }
    public String getCheckListsName(){
        return checkListsName;
    }
    public List<String> getItemNames(){
        return itemNames;
    }
    public List<Boolean> getIsChecked(){
        return isChecked;
    }
    public String getCreatedAt(){
        return createdAt;
    }
}
