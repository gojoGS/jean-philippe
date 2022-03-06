package com.example.demo.security.user.password.validation;

import org.springframework.stereotype.Service;

@Service
public class StrictPasswordValidationService implements PasswordValidationService {
    @Override
    public boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        // TODO implement real logic

        return true;
    }

    @Override
    public String getValidPasswordRegex() {
        return "^$";
    }
}
