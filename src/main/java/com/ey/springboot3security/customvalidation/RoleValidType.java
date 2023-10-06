package com.ey.springboot3security.customvalidation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RoleTypeValidator.class)
public @interface RoleValidType {
   public String message() default "Invalid rolesType, it should be ROLE_USER or ROLE_ADMIN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};   
}