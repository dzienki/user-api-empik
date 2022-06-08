package com.dzienki.userapi.controller;

import com.dzienki.userapi.controller.dto.UserDto;
import com.dzienki.userapi.manager.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserManager userManager;

    @GetMapping("/user/{username}")
    public UserDto getUser(@PathVariable String username){
        return userManager.getUserInformationAndProcess(username);
    }

}
