package com.example.demo.Repository;

import com.example.demo.Entity.HomeCheckListsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeCheckListsRepository extends JpaRepository<HomeCheckListsEntity,Integer> {
}//今のところテストのために作られたRepository
