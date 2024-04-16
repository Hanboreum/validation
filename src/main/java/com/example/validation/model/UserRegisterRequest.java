package com.example.validation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private LocalDateTime registerAt;

}
/*
{
  "name": "이름",
  "age": 30,
  "email": "name@example.com",
  "phone_number": "1234567890",
  "register_at": "2024-04-16T12:00:00"
}
 */