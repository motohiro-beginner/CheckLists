package com.example.demo.Repository;

import com.example.demo.Entity.OneCheckListItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneCheckListItemsRepository extends JpaRepository<OneCheckListItemsEntity,Integer> {
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM OneCheckListItemsEntity o WHERE o.checkList.checkListsId = :id")
    Integer deleteItems(@Param("id") Integer id);
    @Query("SELECT o.itemId FROM OneCheckListItemsEntity o WHERE o.checkList.checkListsId = :id")
    List<Integer> findItemIdByCheckListsId(@Param("id") Integer id);
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM OneCheckListItemsEntity o WHERE o.itemId = :id")
    Integer deleteItemsByItemId(@Param("id") Integer id);
}