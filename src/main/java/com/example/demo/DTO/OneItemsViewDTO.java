package com.example.demo.DTO;

public class OneItemsViewDTO {
    private String itemId;
    private String itemName;
    private boolean isChecked;
    public OneItemsViewDTO(String itemId,String itemName,boolean isChecked){
        this.itemId = itemId;
        this.itemName = itemName;
        this.isChecked = isChecked;
    }
}
