package com.example.demo.Repository;

import com.example.demo.DTO.HomeCheckListDTO;
import com.example.demo.Entity.HomeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HomeRepository extends JpaRepository<HomeUserEntity,Integer> {
    @Query("""
    SELECT new com.example.demo.DTO.HomeCheckListDTO(c.checkListsName,i.itemName,i.isChecked,c.createdAt)
    FROM HomeUserEntity u
    JOIN u.checkLists c
    JOIN c.items i
    WHERE u.userName = :userName
    AND c.createdAt = :createdAt
    """)
    List<HomeCheckListDTO> findAllCheckLists(@Param("userName") String userName,@Param("createdAt") LocalDateTime createdAt);
}
