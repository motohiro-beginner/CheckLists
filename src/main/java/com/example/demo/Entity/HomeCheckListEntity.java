package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
public class HomeCheckListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkListsId;
    @Column(name = "user_id",nullable = false,unique = false)
    private Integer userId;
    @Column(name = "check_lists_name",nullable = false)
    private String checkListsName;
    @Column(name = "created_at",nullable = true)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private HomeUserEntity users;
}
