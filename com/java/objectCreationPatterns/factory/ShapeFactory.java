package com.java.objectCreationPatterns.factory;

import com.java.objectCreationPatterns.factory.factory.Shape;
import com.java.objectCreationPatterns.factory.factoryImpl.Rectangle;
import com.java.objectCreationPatterns.factory.factoryImpl.Square;

public class ShapeFactory {

    public Shape getShapeFactory(String shape) {
        if(shape.equals("Rectangle")) {
            return new Rectangle();
        }
        if(shape.equals("Square")) {
            return new Square();
        }
        throw new IllegalArgumentException("No such shape present");
    }
}
