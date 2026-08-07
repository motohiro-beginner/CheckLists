package com.example.demo.DTO;

import java.util.List;

public class SaveCheckListDTO {
    private String checkListsId;
    private String checkListName;
    private List<SaveItemsDTO> items;
    private String year;
    private String month;
    private String day;
    public SaveCheckListDTO(){
    }
    public String getCheckListsId(){
        return checkListsId;
    }
    public void setCheckListsId(String checkListName){
        this.checkListsId = checkListsId;
    }
    public String getCheckListName(){
        return checkListName;
    }
    public void setCheckListName(){
        this.checkListName = checkListName;
    }
    public List<SaveItemsDTO> getItems(){
        return items;
    }
    public void setItems(List<SaveItemsDTO> items){
        this.items = items;
    }
    public String getYear(){
        return year;
    }
    public void setYear(String year){
        this.year = year;
    }
    public String getMonth(){
        return month;
    }
    public void setMonth(){
        this.month = month;
    }
    public String getDay(){
        return day;
    }
    public void setDay(){
        this.day = day;
    }
}
