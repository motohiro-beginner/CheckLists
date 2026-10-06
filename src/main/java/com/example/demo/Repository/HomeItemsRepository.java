package com.example.demo.Repository;

import com.example.demo.Entity.HomeCheckListsItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HomeItemsRepository extends JpaRepository<HomeCheckListsItemsEntity,Integer> {
    /*該当するユーザーかつ該当するitemIdを持つ列の数を返すJPQL文*/
    @Query("""
            SELECT COUNT(*)
            FROM HomeCheckListsItemsEntity i
            JOIN i.checkList c
            JOIN c.user u
            WHERE u.userName = :userName
            AND i.itemId = :itemId""")
    Integer existsByUserNameAndItemId(@Param("userName") String userName,@Param("itemId") Integer itemId);
}//UpdateCheckServiceで使うRepository
