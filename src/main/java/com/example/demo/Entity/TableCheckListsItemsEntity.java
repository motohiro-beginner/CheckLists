package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "check_lists_items")
public class TableCheckListsItemsEntity {
    /*check_lists_*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column(name = "item_name",nullable = false,length = 50)
    private String itemName;
    @Column(name = "isChecked",nullable = false)
    private boolean isChecked;
    @ManyToOne
    @JoinColumn(name = "check_list_id")
    private TableCheckListsEntity checkList;
    public Integer getItemId(){
        return itemId;
    }
    public void setItemId(Integer itemId){
        this.itemId = itemId;
    }
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public boolean getIsChecked(){
        return isChecked;
    }
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
    public void setCheckListsEntity(TableCheckListsEntity checkList){
        this.checkList = checkList;
    }
}
