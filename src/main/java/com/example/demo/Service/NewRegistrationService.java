package com.example.demo.Service;

import com.example.demo.DTO.NewRegistrationDTO;
import com.example.demo.Entity.NewRegistrationEntity;
import com.example.demo.Repository.NewRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewRegistrationService {
    /*NewRegistrationServiceはアカウント新規作成の際の業務ルールをチェックする。*/
    @Autowired
    private NewRegistrationRepository repository;
    public boolean findByName(String name){
        return repository.existsByName(name);
    }
    public NewRegistrationEntity save(NewRegistrationDTO dto)
}
/*Serviceクラス　例
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}*/