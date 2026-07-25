package com.example.demo.Service;

import com.example.demo.DTO.NewRegistrationDTO;
import com.example.demo.Entity.NewRegistrationEntity;
import com.example.demo.Repository.NewRegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewRegistrationService {
    /*NewRegistrationServiceはアカウント新規作成の際の業務ルールをチェックする。*/
    /*楽観ロックを追加する必要があることに気づいた。*/
    @Autowired
    private NewRegistrationRepository repository;
    public boolean existsByUserName(String userName){
        try {
            return repository.existsByUserName(userName);
        }catch(DataIntegrityViolationException e){
            throw new IllegalArgumentException("登録処理に失敗しました。");
        }
    }
    @Transactional
    public void save(NewRegistrationDTO dto){
        try {
            NewRegistrationEntity registration = new NewRegistrationEntity();
            registration.setUserName(dto.getUserName());
            registration.setPassword(dto.getPassword());
            repository.save(registration);
        }catch(DataIntegrityViolationException e){
            throw new IllegalArgumentException("ユーザー名は既に使用されています。");
        }
    }
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