package com.example.demo.Repository;

import com.example.demo.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Integer> {
    boolean existsByUserName(String userName);
    boolean existsByPassword(String password);
}
