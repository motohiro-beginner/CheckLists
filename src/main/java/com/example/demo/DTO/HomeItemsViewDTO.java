package com.example.demo.DTO;

public class HomeItemsViewDTO {
    private final Integer itemId;
    private final String itemNames;
    private final boolean isChecked;
    public HomeItemsViewDTO(Integer itemId,String itemNames,boolean isChecked){
        this.itemId = itemId;
        this.itemNames = itemNames;
        this.isChecked = isChecked;
    }
    public Integer getItemId(){
        return itemId;
    }
    public String getItemNames(){
        return itemNames;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
}
