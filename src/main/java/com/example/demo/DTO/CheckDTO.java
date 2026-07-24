package com.example.demo.DTO;

public class CheckDTO {
    private boolean isChecked;
    private Integer itemId;
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
    public void setItemId(Integer itemId){
        this.itemId = itemId;
    }
    public Integer getItemId(){
        return itemId;
    }
}
