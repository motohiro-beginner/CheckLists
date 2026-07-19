package com.example.demo.DTO;

import java.util.List;

//Serviceクラスにチェックリストのデータを渡すために用意するDTO
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
    public List<TableItemsViewDTO> getItems(){
        return items;
    }
    public String getCreatedAt(){
        return createdAt;
    }
}
