package com.java.notificationSystem;

import com.java.notificationSystem.dispatcher.DispatchNotification;
import com.java.notificationSystem.entity.Email;
import com.java.notificationSystem.entity.Push;
import com.java.notificationSystem.entity.SMS;
import com.java.notificationSystem.notificationFactory.DefaultNotificationFactory;

public class Application {
    public static void main(String[] args) {
        DefaultNotificationFactory defaultNotificationFactory = new DefaultNotificationFactory();

        DispatchNotification dispatchNotification = new DispatchNotification(defaultNotificationFactory);

        Email email = new Email("toyou@gmail.com", "Test Email", "Testing Notification System");
        SMS sms = new SMS("123453425", "Hey there");
        Push push = new Push("123Devicc", "Push title", "Push payload");

        dispatchNotification.dispatch(email);
        dispatchNotification.dispatch(sms);
        dispatchNotification.dispatch(push);

    }
}
