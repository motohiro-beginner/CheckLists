package com.example.demo.DTO;

public class HomeItemsViewDTO {
    public final String itemNames;
    public final boolean isChecked;
    public HomeItemsViewDTO(String itemNames,boolean isChecked){
        this.itemNames = itemNames;
        this.isChecked = isChecked;
    }
    public String getItemNames(){
        return itemNames;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
}
