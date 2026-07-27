package com.example.demo.DTO;

public class TableSearchDTO {
    private String searchName;
    private String yearSearch;
    private String monthSearch;
    private String daySearch;
    public TableSearchDTO(String searchName,String yearSearch,String monthSearch,String daySearch){
        this.searchName = searchName;
        this.yearSearch = yearSearch;
        this.monthSearch = monthSearch;
        this.daySearch = daySearch;
    }
    public String getSearchName(){
        return searchName;
    }
    public String getYearSearch(){
        return yearSearch;
    }
    public String getMonthSearch(){
        return monthSearch;
    }
    public String getDaySearch(){
        return daySearch;
    }
}
