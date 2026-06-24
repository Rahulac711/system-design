package com.java.notificationSystem.notificationFactory;

import com.java.notificationSystem.entity.Channel;
import com.java.notificationSystem.sender.EmailNotificationSender;
import com.java.notificationSystem.sender.NotificationSender;
import com.java.notificationSystem.sender.PushNotificationSender;
import com.java.notificationSystem.sender.SMSNotificationSender;

import java.util.HashMap;

public class DefaultNotificationFactory {
    private HashMap<Channel, NotificationSender> channelMap = null;

    public DefaultNotificationFactory() {
        channelMap = new HashMap<>();

        EmailNotificationSender email = new EmailNotificationSender();
        SMSNotificationSender sms = new SMSNotificationSender();
        PushNotificationSender push = new PushNotificationSender();

        channelMap.put(Channel.EMAIL, email);
        channelMap.put(Channel.SMS, sms);
        channelMap.put(Channel.PUSH, push);
    }

    public NotificationSender getNotificationByChannel(Channel channel) {
        return channelMap.get(channel);
    }
}
