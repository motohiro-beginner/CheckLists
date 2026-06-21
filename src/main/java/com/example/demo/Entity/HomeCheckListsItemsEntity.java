package com.example.demo.Entity;

import jakarta.persistence.*;

/*CREATE TABLE `check_lists_items` (
`item_id` int NOT NULL AUTO_INCREMENT,
`check_list_id` int NOT NULL,
`item_name` varchar(50) NOT NULL,
`isChecked` tinyint(1) NOT NULL,
PRIMARY KEY (`item_id`),
KEY `check_list_id` (`check_list_id`),
CONSTRAINT `check_lists_items_ibfk_1` FOREIGN KEY (`check_list_id`) REFERENCES `check_lists` (`check_lists_id`)
 */
@Entity
@Table(name = "check_lists_items")
public class HomeCheckListsItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column(name = "check_list_id",nullable = false)
    private Integer checkListId;
    @Column(name = "item_name",nullable = false,length = 50)
    private String itemName;
    @Column(name = "isChecked",nullable = false)
    private boolean isChecked;

}
