package com.example.demo.security.user.restaurant.service.change;

public interface PasswordChangeService {
    void changePassword(String newPassword);

    boolean isCurrentPasswordConfirmationValid(String providedCurrentPassword);
}
