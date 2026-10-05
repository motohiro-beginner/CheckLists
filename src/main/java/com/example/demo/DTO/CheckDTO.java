package com.example.demo.DTO;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Pattern;

public class CheckDTO {
    @NonNull 
    private boolean isChecked;
    @Pattern (regexp = "^[1-9][0-9]*$")
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
