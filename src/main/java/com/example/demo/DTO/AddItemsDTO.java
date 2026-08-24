package com.example.demo.DTO;

public class AddItemsDTO {
    private String itemName;
    private boolean isChecked;
    public AddItemsDTO(){}
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
}
