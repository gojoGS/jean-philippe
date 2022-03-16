package com.example.demo.security.service.password.validation;

import org.springframework.stereotype.Service;

@Service
public class StrictPasswordValidationService implements PasswordValidationService {
    @Override
    public boolean isValid(String password) {
        return password.length() >= 8;
        // TODO implement real logic
    }

    @Override
    public String getValidPasswordRegex() {
        return "^$";
    }
}
