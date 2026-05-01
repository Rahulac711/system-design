package com.java.objectCreationPatterns.factory.factoryImpl;

import com.java.objectCreationPatterns.factory.factory.Shape;

public class Square implements Shape {
    @Override
    public int getArea(int length, int breadth) {
        return length * length;
    }
}
