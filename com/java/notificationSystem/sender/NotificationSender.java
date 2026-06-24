package com.java.notificationSystem.sender;

import com.java.notificationSystem.entity.Notification;

public interface NotificationSender {
    void send(Notification notification);
}
