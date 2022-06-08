package com.dzienki.userapi.mapper;

import com.dzienki.userapi.client.dto.GithubUserDto;
import com.dzienki.userapi.controller.dto.UserDto;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {DateTimeFormatter.class})
public interface UserMapper {

    @Mapping(target = "avatarUrl", source = "githubUserDto.avatar_url")
    @Mapping(target = "createdAt", expression = "java(DateTimeFormatter.ISO_INSTANT.format(githubUserDto.getCreated_at().toInstant()))")
    UserDto map(GithubUserDto githubUserDto);

}
