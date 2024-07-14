package com.vidyayatantechnologies.assignment.annotations;

import com.vidyayatantechnologies.assignment.enums.LogicEnum;
import com.vidyayatantechnologies.assignment.enums.PermissionsEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permissions {
    PermissionsEnum[] permissions();
    LogicEnum type() default LogicEnum.ALL;
}
