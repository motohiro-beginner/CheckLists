package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*SaveItemsDTOはfetch通信で引数を受け取るために用意したDTOである。
* itemIdは変更があった、DBの項目表の行のid列の情報
* itemNamesはその行の項目名の情報
* isCheckedはその行のチェックがついているか否かの情報を格納している。*/
public class SaveItemsDTO {
    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]*$")
    private String itemId;
    @NotBlank
    @Size(max = 50)
    private String itemNames;
    @NotNull
    private boolean isChecked;
    public SaveItemsDTO(){}
    public String getItemId(){
        return itemId;
    }
    public void setItemId(String itemId){
        this.itemId = itemId;
    }
    public String getItemNames(){
        return itemNames;
    }
    public void setItemNames(String itemNames){
        this.itemNames = itemNames;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
}
