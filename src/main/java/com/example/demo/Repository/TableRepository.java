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
            SELECT new com.example.demo.DTO.TableDTO(c.checkListsName,i.itemName,i.isChecked,c.createdAt)
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
}
