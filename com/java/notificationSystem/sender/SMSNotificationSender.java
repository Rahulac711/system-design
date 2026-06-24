package com.java.notificationSystem.sender;

import com.java.notificationSystem.entity.Notification;

public class SMSNotificationSender implements NotificationSender {

    @Override
    public void send(Notification notification) {
        System.out.println("Sending SMS to: "+ notification.getRecipient());
    }
}
