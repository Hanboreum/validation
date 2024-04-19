package com.example.validation.annotation;

import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@NotBlank //not null, if (value = null)
@Size(min = 8, max =8)
@Constraint(validatedBy = {YearMonthValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface YearMonth {

    String message() default "날짜 양식에 맞지 않음 ex) 20240101";
    String pattern() default "yyyyMMdd";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
