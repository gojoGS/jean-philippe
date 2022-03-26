package com.example.demo.backend.session.service;

public interface SessionServiceFactory {
    SessionService get(long sessionId);
}
