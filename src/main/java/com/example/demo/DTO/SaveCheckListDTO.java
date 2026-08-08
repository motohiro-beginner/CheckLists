package com.example.demo.DTO;

import java.util.List;

/*SaveCheckListDTOはfetch通信で引数を受け取るために用意したDTOである。
* checkListIdは変更があった、DBのチェックリスト表の行のIDの情報
* checkListNameはその行のチェックリスト名の情報
* itemsはそのチェックリストidを外部キーに持つ項目表の行の情報
* year,month,dayはチェックリストの列の日付の情報を格納している。*/
public class SaveCheckListDTO {
    private String checkListsId;
    private String checkListName;
    private List<SaveItemsDTO> items;
    private String year;
    private String month;
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
