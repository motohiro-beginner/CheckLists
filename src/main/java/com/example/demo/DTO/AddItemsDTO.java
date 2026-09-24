package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddItemsDTO {
    @NotBlank
    @Size(max = 50)
    private String itemName;
    public AddItemsDTO(){}
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
}
