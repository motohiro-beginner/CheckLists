package com.example.demo.DTO;

import java.time.LocalDate;

public class TableDTO {
    private final String checkListsName;
    private final String itemNames;
    private final boolean isChecked;
    private final LocalDate createdAt;
    public TableDTO(String checkListsName,String itemNames,boolean isChecked,LocalDate createdAt){
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
    public LocalDate getCreatedAt(){
        return createdAt;
    }
}
