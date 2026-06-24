package com.java.notificationSystem.entity;

public class SMS implements Notification {
    String toPhoneNumber;
    String message;

    public SMS(String toPhoneNumber, String message) {
        this.toPhoneNumber = toPhoneNumber;
        this.message = message;
    }

    @Override
    public String getBody() {
        return message;
    }

    @Override
    public String getRecipient() {
        return toPhoneNumber;
    }

    @Override
    public Channel getChannel() {
        return Channel.SMS;
    }
}
