package com.example.demo.DTO;

//一つのチェックリストに対して、複数の項目があるため、TableViewDTOにTableItemsViewDTOのリストを持たせている。
public class TableItemsViewDTO {
    private final String itemName;
    private final boolean isChecked;
    public TableItemsViewDTO(String itemName,boolean isChecked){
        this.itemName = itemName;
        this.isChecked = isChecked;
    }
    public String getItemName(){
        return itemName;
    }
    public boolean isChecked(){
        return isChecked;
    }
}
