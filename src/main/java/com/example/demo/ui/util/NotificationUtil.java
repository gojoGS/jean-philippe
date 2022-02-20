package com.example.demo.ui.util;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import lombok.Getter;

public class NotificationUtil {
    public enum NotificationType {
        ERROR(NotificationVariant.LUMO_ERROR),
        INFO(NotificationVariant.LUMO_PRIMARY),
        SUCCESS(NotificationVariant.LUMO_SUCCESS);

        NotificationType(NotificationVariant variant) {
            this.variant = variant;
        }

        @Getter
        private final NotificationVariant variant;
    }

    public static void showNotification(String message, NotificationType type) {
        Notification notification = Notification.show(message);
        notification.addThemeVariants(type.getVariant());
        notification.setPosition(Notification.Position.BOTTOM_CENTER);
    }

    public static void showSuccess(String message) {
        showNotification(message, NotificationType.SUCCESS);
    }

    public static void showInfo(String message) {
        showNotification(message, NotificationType.INFO);
    }

    public static void showError(String message) {
        showNotification(message, NotificationType.ERROR);
    }
}
