package com.dzienki.userapi.manager;

import com.dzienki.userapi.client.GitHubClient;
import com.dzienki.userapi.controller.dto.UserDto;
import com.dzienki.userapi.entity.UserEntity;
import com.dzienki.userapi.mapper.UserMapper;
import com.dzienki.userapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager {

    private UserRepository userRepository;
    private GitHubClient gitHubClient;
    private UserMapper userMapper;


    public UserDto getUserInformationAndProcess(String username) {
        var githubUser = gitHubClient.getGithubUserData(username);
        incrementRequestCounterOrCreateUser(username);
        return userMapper.map(githubUser);
    }

    public void incrementRequestCounterOrCreateUser(String username) {
        if (userRepository.existsByLogin(username)) {
            userRepository.incrementRequestCount(username);
        } else {
            var user = new UserEntity(username);
            userRepository.save(user);
        }
    }
}
