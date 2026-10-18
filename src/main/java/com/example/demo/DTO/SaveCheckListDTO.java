package com.example.demo.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

/*SaveCheckListDTOはfetch通信で引数を受け取るために用意したDTOである。
* checkListIdは変更があった、DBのチェックリスト表の行のIDの情報
* checkListNameはその行のチェックリスト名の情報
* itemsはそのチェックリストidを外部キーに持つ項目表の行の情報
* year,month,dayはチェックリストの列の日付の情報を格納している。*/
public class SaveCheckListDTO {
    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]*$")
    private String checkListsId;
    @NotBlank
    @Size(max = 50)
    private String checkListName;
    @Valid
    private List<SaveItemsDTO> items;
    @Valid
    private List<NewItemsDTO> newItems;
    @NotBlank
    @Pattern(regexp = "^[1-9]\\d{3}$")
    private String year;
    @NotBlank
    @Pattern(regexp = "^0[1-9]|1[0-2]$")
    private String month;
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])$")
    private String day;
    public SaveCheckListDTO(){
    }
    public String getCheckListsId(){
        return checkListsId;
    }
    public void setCheckListsId(String checkListsId){
        this.checkListsId = checkListsId;
    }
    public String getCheckListName(){
        return checkListName;
    }
    public void setCheckListName(String checkListName){
        this.checkListName = checkListName;
    }
    public List<SaveItemsDTO> getItems(){
        return items;
    }
    public void setItems(List<SaveItemsDTO> items){
        this.items = items;
    }
    public List<NewItemsDTO> getNewItems(){
        return newItems;
    }
    public void setNewItems(List<NewItemsDTO> newItems){
        this.newItems = newItems;
    }
    public String getYear(){
        return year;
    }
    public void setYear(String year){
        this.year = year;
    }
    public String getMonth(){
        return month;
    }
    public void setMonth(String month){
        this.month = month;
    }
    public String getDay(){
        return day;
    }
    public void setDay(String day){
        this.day = day;
    }
}
