package com.java.notificationSystem.sender;

import com.java.notificationSystem.entity.Notification;

import java.time.LocalDateTime;

public class PushNotificationSender implements ScheduledNotificationSender {

    @Override
    public void send(Notification notification) {
        System.out.println("Sending PUSH to: "+ notification.getRecipient());
    }

    @Override
    public void schedule(Notification notification, LocalDateTime dateTime) {
        System.out.println("Scheduling PUSH to: "+notification.getRecipient() + "at" + dateTime);
    }
}