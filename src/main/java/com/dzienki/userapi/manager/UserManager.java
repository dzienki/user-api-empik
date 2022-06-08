package com.dzienki.userapi.manager;

import com.dzienki.userapi.client.GitHubClient;
import com.dzienki.userapi.controller.dto.UserDto;
import com.dzienki.userapi.manager.entity.UserEntity;
import com.dzienki.userapi.mapper.UserMapper;
import com.dzienki.userapi.repository.UserRepository;
import com.dzienki.userapi.utils.CalculationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository userRepository;
    private final GitHubClient gitHubClient;
    private final UserMapper userMapper;


    public UserDto getUserInformationAndProcess(String username) {
        var githubUser = gitHubClient.getGithubUserData(username);
        incrementRequestCounterOrCreateUser(username);
        var user = userMapper.map(githubUser);
        user.setCalculations(CalculationUtils.calculate(githubUser));
        return user;
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
