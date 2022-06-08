package com.dzienki.userapi.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dzienki.userapi.client.GitHubClient;
import com.dzienki.userapi.client.dto.GithubUserDto;
import com.dzienki.userapi.manager.entity.UserEntity;
import com.dzienki.userapi.mapper.UserMapper;
import com.dzienki.userapi.repository.UserRepository;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserManagerTest {

    @Mock
    UserRepository userRepository;
    @Mock
    GitHubClient gitHubClient;
    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    UserManager userManager;


    @Test
    void incrementRequestCounterOrCreateUser_shouldSaveItemToDatabaseWhenNotExist() {
        //given
        var username = "testUsername";
        //when
        when(userRepository.existsByLogin(username)).thenReturn(false);
        when(userRepository.save(any())).thenReturn(new UserEntity());
        userManager.incrementRequestCounterOrCreateUser(username);
        //then
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(0)).incrementRequestCount(any());
    }

    @Test
    void incrementRequestCounterOrCreateUser_shouldIncrementCounter() {
        //given
        var username = "testUsername";
        //when
        when(userRepository.existsByLogin(username)).thenReturn(true);
        doNothing().when(userRepository).incrementRequestCount(username);
        userManager.incrementRequestCounterOrCreateUser(username);
        //then
        verify(userRepository, times(0)).save(any());
        verify(userRepository, times(1)).incrementRequestCount(any());
    }

    @Test
    void getUserInformationAndProcess_shouldReturnCorrectValue() {
        //given
        var username = "testUsername";
        var date = Date.from(Instant.now());
        var githubUserDto = GithubUserDto.builder().id(11).followers(10)
            .public_repos(2).login(username).name("testName").type("type")
            .avatar_url("https://test.com").created_at(date).build();
        //when
        when(gitHubClient.getGithubUserData(username)).thenReturn(githubUserDto);
        when(userRepository.existsByLogin(username)).thenReturn(true);
        doNothing().when(userRepository).incrementRequestCount(username);
        var result = userManager.getUserInformationAndProcess(username);
        //then
        assertEquals(11, result.getId());
        assertEquals("https://test.com", result.getAvatarUrl());
        assertEquals("testName", result.getName());
        assertEquals("type", result.getType());
        assertEquals(
            DateTimeFormatter.ISO_INSTANT.format(githubUserDto.getCreated_at().toInstant()),
            result.getCreatedAt());

    }


}