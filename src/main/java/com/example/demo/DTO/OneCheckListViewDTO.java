package com.example.demo.DTO;

import java.util.List;

public class OneCheckListViewDTO {
    private String checkListId;
    private String checkListName;
    private List<OneItemsViewDTO> items;
    private String createdAt;
    public OneCheckListViewDTO(String checkListId,String checkListName,List<OneItemsViewDTO> items,String createdAt){
        this.checkListId = checkListId;
        this.checkListName = checkListName;
        this.items = List.copyOf(items);
        this.createdAt = createdAt;
    }
}
