package com.example.demo.Repository;

import com.example.demo.Entity.HomeCheckListsItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeItemsRepository extends JpaRepository<HomeCheckListsItemsEntity,Integer> {
}//今のところテストのために作られたRepository
