package com.example.demo.Service;

import com.example.demo.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//Serviceクラスであることを明示
public class LoginService {
    @Autowired//自動で必要なインスタンスを生成してくれる。
     private LoginRepository repository;
    public boolean existByUserName(String userName){
        return repository.existsByUserName(userName);
    }
    public boolean existByPassword(String password){
        return repository.existsByPassword(password);
    }
}
