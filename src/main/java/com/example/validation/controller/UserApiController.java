package com.example.validation.controller;

import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    //가입
    @PostMapping("")
    public UserRegisterRequest register(
            @Valid //검증
            @RequestBody UserRegisterRequest user){
        log.info("userRequest : {}", user);

        return user;
    }
}
