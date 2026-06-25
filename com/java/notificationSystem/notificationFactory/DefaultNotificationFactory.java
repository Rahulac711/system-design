package com.java.notificationSystem.notificationFactory;

import com.java.notificationSystem.entity.Channel;
import com.java.notificationSystem.sender.EmailNotificationSender;
import com.java.notificationSystem.sender.NotificationSender;
import com.java.notificationSystem.sender.PushNotificationSender;
import com.java.notificationSystem.sender.SMSNotificationSender;
import com.java.notificationSystem.sender.ScheduledNotificationSender;

import java.util.HashMap;

public class DefaultNotificationFactory {
    private HashMap<Channel, NotificationSender>          channelMap   = new HashMap<>();
    private HashMap<Channel, ScheduledNotificationSender> scheduledMap = new HashMap<>();

    public DefaultNotificationFactory() {
        EmailNotificationSender email = new EmailNotificationSender();
        SMSNotificationSender   sms   = new SMSNotificationSender();
        PushNotificationSender  push  = new PushNotificationSender();

        channelMap.put(Channel.EMAIL, email);
        channelMap.put(Channel.SMS,   sms);
        channelMap.put(Channel.PUSH,  push);

        scheduledMap.put(Channel.EMAIL, email);
        scheduledMap.put(Channel.PUSH,  push);
        // SMS intentionally omitted — does not support scheduling
    }

    public NotificationSender getSender(Channel channel) {
        return channelMap.get(channel);
    }

    public ScheduledNotificationSender getScheduledSender(Channel channel) {
        return scheduledMap.get(channel);
    }
}
