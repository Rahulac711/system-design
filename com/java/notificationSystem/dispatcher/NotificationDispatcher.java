package com.java.notificationSystem.dispatcher;

import com.java.notificationSystem.entity.Notification;

public interface NotificationDispatcher {
    void dispatch(Notification notification);
}
