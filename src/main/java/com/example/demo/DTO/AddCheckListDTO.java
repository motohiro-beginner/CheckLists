package com.example.demo.DTO;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddCheckListDTO {
    @NotBlank
    @Size(max = 50)
    private String checkListName;
    @NotBlank
    @Pattern(regexp = "^[1-9]\\d{3}$")
    private String year;
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])$")
    private String month;
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])$")
    private String day;
    @NotNull
    private List<AddItemsDTO> items;
    public AddCheckListDTO(){}
    public String getCheckListName(){
        return checkListName;
    }
    public void setCheckListName(String checkListName){
        this.checkListName = checkListName;
    }
    public List<AddItemsDTO> getItems(){
        return List.copyOf(items);
    }
    public void setItems(List<AddItemsDTO> items){
        this.items = List.copyOf(items);
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
