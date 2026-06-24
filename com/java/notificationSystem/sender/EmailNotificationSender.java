package com.java.notificationSystem.sender;

import com.java.notificationSystem.entity.Notification;

import java.time.LocalDateTime;

public class EmailNotificationSender implements ScheduledNotificationSender {

    @Override
    public void send(Notification notification) {
        System.out.println("Sending EMAIL to: "+ notification.getRecipient());
    }

    @Override
    public void schedule(Notification notification, LocalDateTime dateTime) {
        System.out.println("Scheduling EMAIL to: "+ notification.getRecipient() + "at" + dateTime);
    }
}
