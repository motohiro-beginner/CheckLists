package com.example.demo.Repository;

import com.example.demo.Entity.NewRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRegistrationRepository extends JpaRepository<NewRegistrationEntity,Integer> {
}
