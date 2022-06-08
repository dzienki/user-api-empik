package com.dzienki.userapi.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(name = "LOGIN", unique = true)
    String login;
    @Column(name = "REQUEST_COUNT")
    Long requestCount;

    public UserEntity(String login) {
        this.login = login;
        this.requestCount = 1L;
    }

    public UserEntity() {
        this.requestCount = 1L;
    }
}
