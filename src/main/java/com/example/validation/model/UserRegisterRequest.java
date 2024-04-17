package com.example.validation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

   // @NotNull //not null
    //@NotEmpty // not null + not empty
    @NotBlank //not null + not empty + not blank "  "
    private String name;

    @NotBlank
    @Size (min =1, max =12) //pw의 최소 길이는 1, 최대 길이는 12
    private String password;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer age; //문자가 아니기에 notblank 불가능.

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 방식에 맞지 않습니다.") //폰번호 검증 정규식 010-
    private String phoneNumber;

    @FutureOrPresent //현재 혹은 미래
    private LocalDateTime registerAt;

}
/*
{
 "result_code" : "",
 "result+message" : "",
 "data" : {}, //오브젝트 표시
 "error" : {}

  {
    "name": "이름",
    "password": "dfs",
    "age": 30,
    "email": "name@example.com",
    "phone_number": "010-1234-5678",
    "register_at": "2024-04-17T12:00:00"
  }
}
 */