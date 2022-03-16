package com.example.demo.security.service.password.generation;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordGenerationServiceImpl implements PasswordGenerationService{
    @Override
    public String get() {
        return RandomStringUtils.randomAlphanumeric(16, 17);
    }
}
