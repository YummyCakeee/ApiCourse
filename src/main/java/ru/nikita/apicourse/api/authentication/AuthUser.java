package ru.nikita.apicourse.api.authentication;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthUser {
    boolean required() default true;
    Permission[] has() default {};
}
