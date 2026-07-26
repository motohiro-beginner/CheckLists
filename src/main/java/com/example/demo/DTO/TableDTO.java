package com.example.demo.DTO;

import java.time.LocalDate;

public class TableDTO {
    private final Integer checkListsId;
    private final String checkListsName;
    private final Integer itemId;
    private final String itemNames;
    private final boolean isChecked;
    private final LocalDate createdAt;
    public TableDTO(Integer checkListsId,String checkListsName,Integer itemId,String itemNames,boolean isChecked,LocalDate createdAt){
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
