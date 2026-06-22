package com.example.demo.Repository;

import com.example.demo.Entity.HomeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HomeRepository extends JpaRepository<HomeUserEntity,Integer> {
    @Query("""
    SELECT u.
    FROM HomeUserEntity u
    JOIN u.checkLists c
    JOIN c.checkListsId i
    WHERE u.userName = :userName
    AND c.createdAt = :createdAt;
    """)
}
