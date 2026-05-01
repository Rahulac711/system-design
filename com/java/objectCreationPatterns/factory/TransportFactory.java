package com.java.objectCreationPatterns.factory;

import com.java.objectCreationPatterns.factory.factory.Transport;
import com.java.objectCreationPatterns.factory.factoryImpl.Airtransport;
import com.java.objectCreationPatterns.factory.factoryImpl.Water;

public class TransportFactory {

    public Transport getMode(String type) {
        if(type.equals("air")) {
            return new Airtransport();
        }
        if(type.equals("water")) {
            return new Water();
        }
        throw new IllegalArgumentException("No such transport present");
    }
}
