package com.example.demo.DTO;

//一つのチェックリストに対して、複数の項目があるため、TableViewDTOにTableItemsViewDTOのリストを持たせている。
public class TableItemsViewDTO {
    private final String itemId;
    private final String itemName;
    private final boolean isChecked;
    public TableItemsViewDTO(String itemId,String itemName,boolean isChecked){
        this.itemId = itemId;
        this.itemName = itemName;
        this.isChecked = isChecked;
    }
    public Integer getItemId(){
        return itemId;
    }
    public String getItemName(){
        return itemName;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
}
