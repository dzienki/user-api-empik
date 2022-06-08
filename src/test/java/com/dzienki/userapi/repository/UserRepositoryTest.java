package com.dzienki.userapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dzienki.userapi.manager.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void incrementRequestCount_shouldIncrementRequestCounter() {
        //given
        var username = "testUsername";
        var userEntity = new UserEntity(username);
        userRepository.save(userEntity);
        //when
        userRepository.incrementRequestCount(username);
        //then
        var user = userRepository.findById(username);
        assertTrue(user.isPresent());
        assertEquals(2, user.get().getRequestCount());
    }

    @Test
    void existsByLogin_shouldReturnTrue() {
        //given
        var username = "testUsername";
        var userEntity = new UserEntity(username);
        userRepository.save(userEntity);
        //when
        var exists = userRepository.existsByLogin(username);
        //then
        assertTrue(exists);
    }
}