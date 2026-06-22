package com.example.demo.DTO;

import java.time.LocalDateTime;

public class HomeCheckListDTO {
    private String checkListsName;
    private String itemNames;
    private boolean isChecked;
    private LocalDateTime createdAt;
    public HomeCheckListDTO(String checkListsName,String itemNames,boolean isChecked,LocalDateTime createdAt){
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
