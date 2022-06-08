package com.dzienki.userapi.repository;

import com.dzienki.userapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Modifying
    @Query("update UserEntity u set u.requestCount = u.requestCount + 1 where u.login = :login")
    void incrementRequestCount(String login);

    boolean existsByLogin(String login);
}
