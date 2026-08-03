package com.example.demo.Repository;

import com.example.demo.DTO.OneCheckListDTO;
import com.example.demo.Entity.OneCheckListCheckListsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneCheckListRepository extends JpaRepository<OneCheckListCheckListsEntity,Integer> {
    @Query("""
           SELECT new com.example.demo.DTO.OneCheckListDTO(c.checkListId,c.checkListName,i.itemId,i.itemName,i.isChecked,c.createdAt)
           FROM OneCheckListCheckListsEntity c
           JOIN c.items i
           WHERE c.checkListsId = :checkListsId
           """)
    List<OneCheckListDTO> findOneCheckList(@Param("checkListsId") Integer checkListsId);
}
