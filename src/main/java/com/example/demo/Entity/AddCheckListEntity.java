package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "check_lists")
public class AddCheckListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkListId;
    @Column(name = "check_lists_name",nullable = false,unique = false)
    private String checkListName;
    @Column(name = "created_at",nullable = false)
    private LocalDate createdAt;
    @OneToMany(mappedBy = "checkList")
    private List<AddItemsEntity> items;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AddUserEntity user;
    public Integer getCheckListId(){
        return checkListId;
    }
    public void setCheckListId(Integer checkListId){
        this.checkListId = checkListId;
    }
    public String getCheckListName(){
        return checkListName;
    }
    public void setCheckListName(String checkListName){
        this.checkListName = checkListName;
    }
    public LocalDate getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }
}
