package com.example.demo.DTO;

public class HomeItemsViewDTO {
    private final String itemId;
    private final String itemNames;
    private final boolean isChecked;
    public HomeItemsViewDTO(String itemId,String itemNames,boolean isChecked){
        this.itemId = itemId;
        this.itemNames = itemNames;
        this.isChecked = isChecked;
    }
    public String getItemId(){
        return itemId;
    }
    public String getItemNames(){
        return itemNames;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
}
