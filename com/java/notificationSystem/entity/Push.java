package com.java.notificationSystem.entity;

public class Push implements Notification {
    private String toDeviceId;
    private String title;
    private String payload;

    public Push(String toDeviceId,
                String title,
                String payload) {
        this.payload = payload;
        this.title = title;
        this.toDeviceId = toDeviceId;
    }

    @Override
    public String getBody() {
        return payload;
    }

    @Override
    public String getRecipient() {
        return toDeviceId;
    }

    @Override
    public Channel getChannel() {
        return Channel.PUSH;
    }

    public String getTitle() {
        return title;
    }
}
