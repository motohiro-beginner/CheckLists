package com.example.demo.Repository;

import com.example.demo.DTO.HomeCheckListsDTO;
import com.example.demo.Entity.HomeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<HomeUserEntity,Integer> {
    /*users表とcheck_lists表とcheck_lists_items表を結合し、該当のユーザー名かつ該当の日付の行の
    チェックリスト名,項目名,各項目にチェックされているか否か,作成日を取り出す。*/
    @Query("""
    SELECT new com.example.demo.DTO.HomeCheckListsDTO(c.checkListsId,c.checkListsName,i.itemId,i.itemName,i.isChecked,c.createdAt)
    FROM HomeUserEntity u
    JOIN u.checkLists c
    JOIN c.items i
    WHERE u.userName = :userName
    AND c.createdAt = :createdAt
    """)
    List<HomeCheckListsDTO> findAllCheckLists(@Param("userName") String userName, @Param("createdAt") LocalDate createdAt);
    @Query("""
    SELECT COUNT(u)
    FROM HomeUserEntity u
    JOIN u.checkLists c
    WHERE u.userName = :userName
    AND c.createdAt = :createdAt
    """)
    Integer existsByUserNameAndCreatedAt(@Param("userName") String userName, @Param("createdAt") LocalDate createdAt);
}
