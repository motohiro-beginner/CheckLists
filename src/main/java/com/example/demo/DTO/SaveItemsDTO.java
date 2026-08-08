package com.example.demo.DTO;

/*SaveItemsDTOはfetch通信で引数を受け取るために用意したDTOである。
* itemIdは変更があった、DBの項目表の行のid列の情報
* itemNamesはその行の項目名の情報
* isCheckedはその行のチェックがついているか否かの情報を格納している。*/
public class SaveItemsDTO {
    private String itemId;
    private String itemNames;
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
