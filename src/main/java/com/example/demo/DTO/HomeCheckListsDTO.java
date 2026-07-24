package com.example.demo.DTO;

import java.time.LocalDate;
/*HomeCheckListsDTOはHomeRepositoryに定義したメソッドが複数のデータを戻り値として返すために作成したDTOである。*/
public class HomeCheckListsDTO {
    private final Integer checkListsId;
    private final String checkListsName;
    private final Integer itemId;
    private final String itemNames;
    private final boolean isChecked;
    private final LocalDate createdAt;
    public HomeCheckListsDTO(Integer checkListsId,String checkListsName, Integer itemId,String itemNames, boolean isChecked, LocalDate createdAt){
        this.checkListsId = checkListsId;
        this.checkListsName = checkListsName;
        this.itemId = itemId;
        this.itemNames = itemNames;
        this.isChecked = isChecked;
        this.createdAt = createdAt;
    }
    public Integer getCheckListsId(){
        return checkListsId;
    }
    public String getCheckListsName(){
        return checkListsName;
    }
    public Integer getItemId(){
        return itemId;
    }
    public String getItemNames(){
        return itemNames;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
    public LocalDate getCreatedAt(){
        return createdAt;
    }
}
