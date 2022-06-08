package com.dzienki.userapi.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.dzienki.userapi.client.dto.GithubUserDto;
import org.junit.jupiter.api.Test;

class CalculationUtilsTest {

    @Test
    void calculate_shouldReturnCorrectValue() {
        //given
        var githubUserDto = new GithubUserDto();
        githubUserDto.setFollowers(10);
        githubUserDto.setPublic_repos(2);
        //when
        var result = CalculationUtils.calculate(githubUserDto);
        //then
        assertEquals(2.4, result);
    }

    @Test
    void calculate_shouldReturnInfinityValueWhenPassing0Followers() {
        //given
        var githubUserDto = new GithubUserDto();
        githubUserDto.setFollowers(0);
        githubUserDto.setPublic_repos(2);
        //when
        var result = CalculationUtils.calculate(githubUserDto);
        //then
        assertTrue(Double.isInfinite(result));
    }

}