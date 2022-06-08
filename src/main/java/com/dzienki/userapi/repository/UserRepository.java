package com.dzienki.userapi.repository;

import com.dzienki.userapi.manager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.requestCount = u.requestCount + 1 where u.login = :login")
    void incrementRequestCount(@Param("login") String login);

    boolean existsByLogin(String login);
}
