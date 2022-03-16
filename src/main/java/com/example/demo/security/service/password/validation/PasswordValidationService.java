package com.example.demo.security.service.password.validation;

public interface PasswordValidationService {
    boolean isValid(String password);

    String getValidPasswordRegex();
}
