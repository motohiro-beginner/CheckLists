package com.example.demo.Repository;

import com.example.demo.Entity.TableCheckListsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableCheckListsRepository extends JpaRepository<TableCheckListsEntity,Integer> {
}//今のところテストをするために仮の値を登録するためにつくられたRepository
