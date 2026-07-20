package com.example.demo.Repository;

import com.example.demo.Entity.TableCheckListsItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableItemsRepository extends JpaRepository<TableCheckListsItemsEntity,Integer> {
}//今のところテストをするために仮の値を登録するためにつくられたRepository
