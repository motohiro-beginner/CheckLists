package com.example.demo.DTO;

public class TableItemsViewDTO {
    private final String itemName;
    private final boolean isChecked;
    public TableItemsViewDTO(String itemName,boolean isChecked){
        this.itemName = itemName;
        this.isChecked = isChecked;
    }
    public String getItemName(){
        return itemName;
    }
    public boolean isChecked(){
        return isChecked;
    }
}
