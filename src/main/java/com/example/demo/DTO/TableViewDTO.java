package com.example.demo.DTO;

import java.util.List;

//Serviceクラスにチェックリストのデータを渡すために用意するDTO
public class TableViewDTO {
    private final Integer checkListsId;
    private final String checkListsName;
    private final List<TableItemsViewDTO> items;
    private final String createdAt;
    public TableViewDTO(Integer checkListsId,String checkListsName,List<TableItemsViewDTO> items,String createdAt){
        this.checkListsId = checkListsId;
        this.checkListsName = checkListsName;
        this.items = List.copyOf(items);
        this.createdAt = createdAt;
    }
    public Integer getCheckListsId(){
        return checkListsId;
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
