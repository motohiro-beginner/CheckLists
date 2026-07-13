package com.example.demo.DTO;

import java.util.List;

public class TableViewDTO {
    private final String checkListsName;
    private final List<TableItemsViewDTO> items;
    private final String createdAt;
    public TableViewDTO(String checkListsName,List<TableItemsViewDTO> items,String createdAt){
        this.checkListsName = checkListsName;
        this.items = List.copyOf(items);
        this.createdAt = createdAt;
    }
    public String getCheckListsName(){
        return checkListsName;
    }
    public List<TableItemsViewDTO> items(){
        return items;
    }
    public String getCreatedAt(){
        return createdAt;
    }
}
