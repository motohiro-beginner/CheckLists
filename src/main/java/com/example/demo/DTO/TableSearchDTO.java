package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TableSearchDTO {
    @Size(max = 50)
    private String searchName;
    @Pattern(regexp = "^[1-9]\\d{3}$")
    private String yearSearch;
    @Pattern(regexp = "^0[1-9]|1[0-2]$")
    private String monthSearch;
    @Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])$")
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
