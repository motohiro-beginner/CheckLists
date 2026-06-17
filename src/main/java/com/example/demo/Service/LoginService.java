package com.example.demo.Service;

import com.example.demo.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
     private LoginRepository repository;
    public boolean existByUserName(String userName){
        return repository.existsByUserName(userName);
    }
    public boolean existByPassword(String password){
        return repository.existsByPassword(password);
    }
}
