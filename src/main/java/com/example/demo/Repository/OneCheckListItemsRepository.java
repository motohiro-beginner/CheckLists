package com.example.demo.Repository;

import com.example.demo.Entity.OneCheckListItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneCheckListItemsRepository extends JpaRepository<OneCheckListItemsEntity,Integer> {
}
