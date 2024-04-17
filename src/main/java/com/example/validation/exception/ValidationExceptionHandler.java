package com.example.validation.exception;

import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Api> validException(
            MethodArgumentNotValidException exception
    ){

        log.error("exception" ,exception);

        var errorMessageList = exception.getFieldErrors().stream() //스트림 통해 에러가 난 필드를 가져옴
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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);


    }


}
  /*
        {
    "result_code": "Bad Request",
    "result_message": null,
    "data": null,
    "error": {
        "error_message": [
            "data.name : {  } 은 공백일 수 없습니다 "
        ]
    }
}

         */
/*
{
    "timestamp": "2024-04-17T02:47:38.820+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/api/user"
}

 */

/*

@ExceptionHandler(value ={ ParseException.class})
    public ResponseEntity<Api> parseException (ParseException e) {
        log.error("error", e);
        String rejectedValue = e.getMessage();

        // 에러 메시지 구성
        String errorDetail = String.format("Parsing failed for value '%s'", rejectedValue);

        // 에러 객체 생성
        Api.Error error = Api.Error.builder()
                .errorMessage(Collections.singletonList(errorDetail))
                .build();

        // API 응답 객체 생성
        Api<Object> errorResponse = Api.builder()
                .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(error)
                .build();

        // ResponseEntity 생성하여 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
 */