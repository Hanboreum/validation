package com.example.validation.controller;

import com.example.validation.model.UserRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    //가입
    @GetMapping("")
    public UserRegisterRequest register(@RequestBody UserRegisterRequest user){
        log.info("userRequest : {}", user);

        return user;
    }
}
