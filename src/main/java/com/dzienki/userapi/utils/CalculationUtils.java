package com.dzienki.userapi.utils;

import com.dzienki.userapi.client.dto.GithubUserDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CalculationUtils {


    public static double calculate(GithubUserDto githubUserDto) {
        return 6.0 / githubUserDto.getFollowers() * (2 + githubUserDto.getPublic_repos());
    }
}
