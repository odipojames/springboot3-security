package com.ey.springboot3security.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class RoleTypeValidator  implements ConstraintValidator<RoleValidType, String> {

    @Override
    public boolean isValid(String roleType, ConstraintValidatorContext constraintValidatorContext) {
    List<String> roleTypes = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        return roleTypes.contains(roleType);
    }
}