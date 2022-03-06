package com.example.demo.security.user.restaurant.service.change;

public interface PasswordChangeServiceFactory {
    PasswordChangeService get(long userId);
}
