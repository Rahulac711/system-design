package com.java.notificationSystem.entity;

public class Email implements Notification {
    private String title;
    private String body;
    private String toEmail;

    public Email(String toEmail, String title, String body) {
        this.title = title;
        this.body = body;
        this.toEmail = toEmail;
    }

    @Override
    public String getRecipient() {
        return toEmail;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public Channel getChannel() {
        return Channel.EMAIL;
    }

    public String getTitle() {
        return title;
    }
}
