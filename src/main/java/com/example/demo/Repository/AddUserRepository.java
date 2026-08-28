package com.example.demo.Repository;

import com.example.demo.Entity.AddUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddUserRepository extends JpaRepository<AddUserEntity,Integer> {
    AddUserEntity findByUserName(@Param("userName") String userName);
}
