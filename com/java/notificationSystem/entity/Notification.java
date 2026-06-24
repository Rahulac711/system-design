package com.java.notificationSystem.entity;

// contract
public interface Notification {
    String getBody();
    String getRecipient();
    Channel getChannel();
}
