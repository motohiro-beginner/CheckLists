package com.example.demo.Repository;

import com.example.demo.Entity.OneCheckListUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OneCheckListUserRepository extends JpaRepository<OneCheckListUserEntity,Integer> {
    //該当するcheckListIdかつ該当するuserNameの列を取り出すためのメソッド
    @Query("""
    SELECT COUNT(*)
    FROM OneCheckListUserEntity u
    JOIN u.checkList c
    WHERE c.checkListsId = :checkListsId
    AND u.userName = :userName
""")
    Integer existsByCheckListIdAndUserName(@Param("checkListsId") Integer checkListsId,@Param("userName") String userName);
}
