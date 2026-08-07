package com.example.demo.DTO;

public class SaveItemsDTO {
    private String itemId;
    private String itemNames;
    private boolean isChecked;
    public SaveItemsDTO(){}
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
