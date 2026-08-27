package com.example.demo.DTO;

public class NewItemsDTO {
    private String itemName;
    private boolean isChecked;
    public NewItemsDTO(){}
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
}
