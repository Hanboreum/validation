package com.example.validation.model;

import com.example.validation.annotation.PhoneNumber;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

   // @NotNull //not null
    //@NotEmpty // not null + not empty
   //@NotBlank //not null + not empty + not blank "  "
    private String name;
    private String nickName; //name or nickName 둘 중 하나가 있어야 함

    @NotBlank
    @Size (min =1, max =12) //pw의 최소 길이는 1, 최대 길이는 12
    private String password;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer age; //문자가 아니기에 notblank 불가능.

    @Email
    private String email;

    //@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 방식에 맞지 않습니다.") //폰번호 검증 정규식 010-
    @PhoneNumber
    private String phoneNumber;

    @FutureOrPresent //현재 혹은 미래
    private LocalDateTime registerAt;

   // @AssertFalse 해당 값이 false여야함 default return 을 true 줄 때 사용
    @AssertTrue(message = "name or nickname 은 존재해야 합니다.")// 해당 return 이 true 일 때 실행.
       public boolean isNameCheck(){ //is는 필수
        if (Objects.nonNull(name) && !name.isBlank()){
            return true;//name object가 null이 아니어야 하고, name이 blank가 아니면 통과
        }
        if(Objects.nonNull(nickName) && !nickName.isBlank()){
            return true;//nickname이 not null이어야 하고, blank가 아니어야  통과
        }
        return false;
    }

}
/*

  @AssertTrue(message = "name or nickname 은 존재해야함")
 public boolean isnameCheck(){
     if(!name.isBlank()){
      return true;
     }
     if(!nickName.isBlank()){
      return true;
     }
     return false;
    }
    ---
{
  "result_code": "200",
  "result_message": "Success",
  "data": {
    "name": "ㄹㄹ ",
    "nick_name" : "ㄹㅇ",
    "password": "123",
    "age": 30,
    "email": "name@example.com",
    "phone_number": "010-1234-5678",
    "register_at": "2024-04-17T17:00:00"
  },
  "error": {
    "error_message": [
      "Error occurred during processing"
    ]
  }
} */