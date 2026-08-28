package com.example.demo.Repository;

import com.example.demo.Entity.AddItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddItemsRepository extends JpaRepository<AddItemsEntity,Integer> {
}
