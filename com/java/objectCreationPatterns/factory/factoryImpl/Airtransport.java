package com.java.objectCreationPatterns.factory.factoryImpl;

import com.java.objectCreationPatterns.factory.factory.Transport;

public class Airtransport implements Transport {
    @Override
    public void deliver() {
        System.out.println("Deliver by Air Transport");
    }
}
