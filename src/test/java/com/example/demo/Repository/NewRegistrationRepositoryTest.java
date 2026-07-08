package com.example.demo.Repository;

import com.example.demo.Entity.NewRegistrationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class NewRegistrationRepositoryTest {
    @Autowired
    NewRegistrationRepository repository;
    @Test
    void repositoryTest(){
        /*テストデータ1
        NewRegistrationEntity entity = new NewRegistrationEntity();
        entity.setUserName("abcdefg");
        entity.setPassword("qwerty12");
        boolean result = repository.existsByUserName("abcdefghi");
        assertFalse(result);
        //existsByUserNameの引数と同じuserNameがdbに登録されていなかった場合falseになるかをチェックする。
*/
        /*
        NewRegistrationEntity entity = new NewRegistrationEntity();
        entity.setUserName("abcdefg");
        entity.setPassword("qwerty12");
        repository.save(entity);
        boolean result = repository.existsByUserName("abcdefg");
        assertTrue(result);
        //existsByUserNameの引数と同じuserNameがdbに登録されていた場合trueになるかをチェックする。
         */
    }
}
