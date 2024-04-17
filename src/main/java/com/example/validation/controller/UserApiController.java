package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public Api<? extends Objects> register( //요청 할 때도 Api스펙을 통해 리턴.
                                            //Api<? extends Objects> , wildcard. 어떤 타입인진 모르지만 object 상속 받은 애를 리턴할거야
                                  @Valid //검증
            @RequestBody Api<UserRegisterRequest> user, //요청도 Api스펙으로 요청
                                  BindingResult bindingResult){  //해당 valid 실행시 결과를 bindingresult에 담아줌
        //이제 서버는 항상Api라는 스펙을 통해서만 제이슨을 보낼 수 있음
        log.info("userRequest : {}", user);

        if(bindingResult.hasErrors()){ //bindingResult를 통해 에러 유무 검사
            var errorMessageList = bindingResult.getFieldErrors().stream() //스트림 통해 에러가 난 필드를 가져옴
                    //stream을 통해 list -> map으로 바꿔줌
                    .map(it -> { //값 변환 (map = 값 변환시 사용)
                        var format = "%s : { %s } 은 %s ";
                        var messages = String.format(format, it.getField(),it.getRejectedValue(),it.getDefaultMessage());
                                                //어떤 에러가 났는지 getField, 거절당한 값, 에러 메세지. 어떤 필드가 어떤 값을 요청했기에 이런 이유로 거절 당함.
                        return messages;
                    }).collect(Collectors.toList()); //에러메세지 리스트를 만든다.

            var error = Api.Error
                    .builder()
                    .errorMessage(errorMessageList).build();

            var errorResponse = Api.builder()
                    .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .resultCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .error(error)
                    .build();

            return errorResponse;
        }

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
