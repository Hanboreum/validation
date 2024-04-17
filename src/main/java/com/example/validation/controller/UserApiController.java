package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    //가입
    @PostMapping("")
    public  Api<UserRegisterRequest>  register( //요청 할 때도 Api스펙을 통해 리턴.
                                                          //Api<? extends Objects> , wildcard. 어떤 타입인진 모르지만 object 상속 받은 애를 리턴할거야
                                                          @Valid //검증
            @RequestBody Api<UserRegisterRequest> user //요청도 Api스펙으로 요청
                                                          ){  //해당 valid 실행시 결과를 bindingresult에 담아줌
        //이제 서버는 항상Api라는 스펙을 통해서만 제이슨을 보낼 수 있음
        log.info("userRequest : {}", user);

        var body = user.getData();
        Api<UserRegisterRequest> response = Api.<UserRegisterRequest>builder()
                .data(body)
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .build();
        //빌더 통해 해당 데이터 빌드, response응답 내림
        return response;
    }
}
