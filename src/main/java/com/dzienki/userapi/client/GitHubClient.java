package com.dzienki.userapi.client;

import com.dzienki.userapi.client.dto.GithubUserDto;
import com.dzienki.userapi.exception.InternalServerException;
import com.dzienki.userapi.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;

@Service
public class GitHubClient {

    private final String ENDPOINT_URL = "https://api.github.com/users/";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GitHubClient(ObjectMapper objectMapper) {
        httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.of(30, ChronoUnit.SECONDS))
            .build();
        this.objectMapper = objectMapper;
    }

    public GithubUserDto getGithubUserData(String username) {
        HttpRequest request = HttpRequest.newBuilder()
            .header("Accept", "application/vnd.github.v3+json")
            .uri(URI.create(ENDPOINT_URL + username))
            .build();

        try {
            HttpResponse<String> response =
                httpClient.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), GithubUserDto.class);
            } else if (response.statusCode() == 404) {
                throw new NotFoundException("User not found");
            } else {
                throw new InternalServerException();
            }
        } catch (IOException | InterruptedException e) {
            throw new InternalServerException(e);
        }

    }


}

