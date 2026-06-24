package com.java.notificationSystem.dispatcher;

import com.java.notificationSystem.entity.Notification;
import com.java.notificationSystem.notificationFactory.DefaultNotificationFactory;

public class DispatchNotification implements NotificationDispatcher {

    DefaultNotificationFactory notificationFactory = null;

    public DispatchNotification(DefaultNotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    @Override
    public void dispatch(Notification notification) {
        notificationFactory.getNotificationByChannel(notification.getChannel())
                .send(notification);
    }
}
