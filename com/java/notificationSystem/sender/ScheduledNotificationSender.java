package com.java.notificationSystem.sender;

import com.java.notificationSystem.entity.Notification;

import java.time.LocalDateTime;

public interface ScheduledNotificationSender extends  NotificationSender {
    void schedule(Notification notification, LocalDateTime dateTime);
}
