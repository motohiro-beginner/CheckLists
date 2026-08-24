package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.List;

public class AddCheckListDTO {
    private String checkListName;
    private List<AddItemsDTO> items;
    private LocalDate createdAt;
    public AddCheckListDTO(){}
    public String getCheckListName(){
        return checkListName;
    }
    public void setCheckListName(String checkListName){
        this.checkListName = checkListName;
    }
    public List<AddItemsDTO> getItems(){
        return List.copyOf(items);
    }
    public void setItems(List<AddItemsDTO> items){
        this.items = List.copyOf(items);
    }
    public LocalDate getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }
}
