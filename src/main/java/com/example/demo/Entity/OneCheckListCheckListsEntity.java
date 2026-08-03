package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "check_lists")
public class OneCheckListCheckListsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkListsId;
    @Column(name = "check_lists_name",nullable = false,unique = false)
    private String checkListsName;
    @Column(name = "created_at",nullable = false)
    private LocalDate createdAt;
    @OneToMany(mappedBy = "checkList")
    private List<OneCheckListItemsEntity> items;
    public Integer getCheckListsId(){
        return checkListsId;
    }
    public void setCheckListsId(Integer checkListsId){
        this.checkListsId = checkListsId;
    }
    public String getCheckListsName(){
        return checkListsName;
    }
    public void setCheckListsName(String checkListsName){
        this.checkListsName = checkListsName;
    }
    public LocalDate getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }

}
