package com.java.objectCreationPatterns.factory.factoryImpl;

import com.java.objectCreationPatterns.factory.factory.Transport;

public class Water implements Transport {

    @Override
    public void deliver() {
        System.out.println("Deliver by Water Transport");
    }
}
