package com.ey.springboot3security.customvalidation;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.ey.springboot3security.repository.UserInfoRepository;
import org.springframework.http.ResponseEntity;
import com.ey.springboot3security.entity.UserInfo;
import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserInfoRepository userRepository;

    @Override
    public void initialize(UniqueEmail constraint) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // Check if the email already exists in the database
        Optional<UserInfo> existingUser = userRepository.findByEmail(email);
        return existingUser == null;
    }
}
