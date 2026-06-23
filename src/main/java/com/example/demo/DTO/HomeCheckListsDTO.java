package com.example.demo.DTO;

import java.time.LocalDateTime;
/*HomeCheckListsDTOはHomeRepositoryに定義したメソッドが複数のデータを戻り値として返すために作成したDTOである。*/
public class HomeCheckListsDTO {
    private final String checkListsName;
    private final String itemNames;
    private final boolean isChecked;
    private final LocalDateTime createdAt;
    public HomeCheckListsDTO(String checkListsName, String itemNames, boolean isChecked, LocalDateTime createdAt){
        this.checkListsName = checkListsName;
        this.itemNames = itemNames;
        this.isChecked = isChecked;
        this.createdAt = createdAt;
    }
    public String getCheckListsName(){
        return checkListsName;
    }
    public String getItemNames(){
        return itemNames;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
}
