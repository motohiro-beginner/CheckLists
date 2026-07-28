package com.example.demo.Repository;

import com.example.demo.DTO.TableDTO;
import com.example.demo.Entity.TableUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableUserEntity,Integer> {
    /*該当するユーザーの全てのチェックリストをdbから取得するメソッドである。*/
    @Query("""
            SELECT new com.example.demo.DTO.TableDTO(c.checkListsId,c.checkListsName,i.itemId,i.itemName,i.isChecked,c.createdAt)
            FROM TableUserEntity u
            JOIN u.checkLists c
            JOIN c.items i
            WHERE u.userName = :userName
            """)
    List<TableDTO> findTableAllCheckLists(@Param("userName") String userName);
    /*該当するユーザーのチェックリストが何個あるかを返すメソッドである。*/
    @Query("""
            SELECT COUNT(u)
            FROM  TableUserEntity u
            JOIN u.checkLists c
            WHERE u.userName = :userName""")
    Integer existsByUserCheckLists(@Param("userName") String userName);
    /*該当するユーザーの、検索されたチェックリスト名,年,月,日に一致するチェックリストを検索するメソッドである。*/
    @Query("""
    SELECT new com.example.demo.DTO.TableDTO(c.checkListsId,c.checkListsName,i.itemId,i.isChecked,c.createdAt)
    FROM TableUserEntity u
    JOIN u.checkLists c
    JOIN c.items i
    WHERE u.userName = :userName
    AND (:keyword IS NULL OR c.checkListsName LIKE :keyword)
    AND (:year IS NULL OR FUNCTION('YEAR',c.createdAt) = :year)
    AND (:month IS NULL OR FUNCTION('MONTH',c.createdAt) = :month)
    AND (:day IS NULL OR FUNCTION('DAY',c.createdAt) = :day)
    """)
    //Serviceクラスから、ワイルドカードつきの文字列が送られてくる。
    List<TableDTO> findSearchedCheckLists(@Param("userName") String userName,
                                          @Param("keyword") String keyword,
                                          @Param("year") Integer year,
                                          @Param("month") Integer month,
                                          @Param("day") Integer day);
    @Query("""
    SELECT COUNT(u)
    FROM TableUserEntity u
    JOIN u.checkLists c
    WHERE u.userName = :userName
    AND (:keyword IS NULL OR c.checkListsName LIKE :keyword)
    AND (:year IS NULL OR FUNCTION('YEAR',c.createdAt) = :year)
    AND (:month IS NULL OR FUNCTION('MONTH',c.createdAt) = :month)
    AND (:day IS NULL OR FUNCTION('DAY',c.createdAt) = :day)
    """)
    //Serviceクラスから、ワイルドカードつきの文字列が送られてくる。
    Integer existsBySearchedCheckLists(@Param("userName") String userName,
                                       @Param("keyword") String keyword,
                                       @Param("year") Integer year,
                                       @Param("month") Integer month,
                                       @Param("day") Integer day);
}
