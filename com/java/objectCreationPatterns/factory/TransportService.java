package com.java.objectCreationPatterns.factory;


import com.java.objectCreationPatterns.factory.factory.Transport;

public class TransportService {

    void send(String mode) {
        TransportFactory transportFactoy = new TransportFactory();
        Transport sendVia = transportFactoy.getMode(mode);
        sendVia.deliver();
    }
}
