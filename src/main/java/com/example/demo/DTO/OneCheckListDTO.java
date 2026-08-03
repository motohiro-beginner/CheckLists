package com.example.demo.DTO;

import java.time.LocalDate;

public class OneCheckListDTO {
    private Integer checkListId;
    private String checkListName;
    private Integer itemId;
    private String itemName;
    private boolean isChecked;
    private LocalDate createdAt;
    public OneCheckListDTO(Integer checkListId,String checkListName,Integer itemId,String itemName,boolean isChecked,LocalDate createdAt){
        this.checkListId = checkListId;
        this.checkListName = checkListName;
        this.itemId = itemId;
        this.itemName = itemName;
        this.isChecked = isChecked;
        this.createdAt = createdAt;
    }
    public Integer getCheckListId(){
        return checkListId;
    }
    public String getCheckListName(){
        return checkListName;
    }
    public Integer getItemId(){
        return itemId;
    }
    public String getItemName(){
        return itemName;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
    public LocalDate getCreatedAt(){
        return createdAt;
    }
}