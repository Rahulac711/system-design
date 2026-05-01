package com.java.objectCreationPatterns.factory.factoryImpl;

import com.java.objectCreationPatterns.factory.factory.Shape;

public class Rectangle implements Shape {
    @Override
    public int getArea(int length, int breadth) {
        return length * breadth;
    }
}
