package com.example.demo.security.service.id;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerationServiceImpl implements IdGenerationService {

    @Override
    public String get() {
        // TOPIC UUID
        return UUID.randomUUID().toString();
    }
}
