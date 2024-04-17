package com.example.validation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {

    private String resultCode;
    private String resultMessage;

    @Valid //검증한다고 지정
    private T data;
    private Error error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Error{
        //에러를 담아줄 에러 객체
        private List<String> errorMessage;
    }

}
/*
{
 "result_code" : "",
 "result+message" : "",
 "data" : {}, //오브젝트 표시
 "error" : { error_message}
}
---
{
  "result_code": "200",
  "result_message": "Success",
  "data": {
    "name": "이름",
    "password": "dfs",
    "age": 30,
    "email": "name@example.com",
    "phone_number": "010-1234-5678",
    "register_at": "2024-04-17T12:00:00"
  },
  "error": {
    "error_message": [
      "Error occurred during processing"
    ]
  }
}

 */