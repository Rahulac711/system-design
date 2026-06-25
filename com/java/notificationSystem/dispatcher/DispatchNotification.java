package com.java.notificationSystem.dispatcher;

import com.java.notificationSystem.entity.Notification;
import com.java.notificationSystem.notificationFactory.DefaultNotificationFactory;
import com.java.notificationSystem.sender.ScheduledNotificationSender;

import java.time.LocalDateTime;

public class DispatchNotification implements NotificationDispatcher {

    DefaultNotificationFactory notificationFactory = null;

    public DispatchNotification(DefaultNotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    @Override
    public void dispatch(Notification notification) {
        notificationFactory.getSender(notification.getChannel())
                .send(notification);
    }

    @Override
    public void dispatchSchedule(Notification notification, LocalDateTime dateTime) {
        ScheduledNotificationSender sender = notificationFactory.getScheduledSender(notification.getChannel());
        if (sender == null) throw new UnsupportedOperationException(
                notification.getChannel() + " does not support scheduling");
        sender.schedule(notification, dateTime);
    }
}
