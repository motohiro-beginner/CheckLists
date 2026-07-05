package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/*check_lists表は
* CREATE TABLE `check_lists` (
* check_lists_id int NOT NULL AUTO_INCREMENT,
* user_id int NOT NULL,
* check_lists_name varchar(50) NOT NULL,
* created_at datetime DEFAULT NULL,
* PRIMARY KEY (`check_lists_id`),
* KEY `user_id` (`user_id`),
* */
@Entity
@Table(name = "check_lists")
public class HomeCheckListsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkListsId;
    @Column(name = "check_lists_name",nullable = false,unique = true)
    private String checkListsName;
    @Column(name = "created_at",nullable = false)
    private LocalDate createdAt;
    public HomeUserEntity getUserId(){
        return userId;
    }
    public void setUserId(HomeUserEntity userId){
        this.userId = userId;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private HomeUserEntity userId;
    @OneToMany(mappedBy = "itemId")
    private List<HomeCheckListsItemsEntity> items;
}
