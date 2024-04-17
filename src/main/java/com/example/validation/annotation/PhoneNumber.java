package com.example.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //언제 실행? 런타임에만
public @interface PhoneNumber {

    String message() default "폰 번호 양식에 맞지 않음 ex)010 -0000-0000";

    String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";
}
