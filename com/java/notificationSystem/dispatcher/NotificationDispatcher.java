package com.java.notificationSystem.dispatcher;

import com.java.notificationSystem.entity.Notification;

import java.time.LocalDateTime;

public interface NotificationDispatcher {
    void dispatch(Notification notification);
    void dispatchSchedule(Notification notification, LocalDateTime dateTime);
}
