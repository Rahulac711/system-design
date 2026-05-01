package com.java.objectCreationPatterns.factory;

import com.java.objectCreationPatterns.factory.factory.Shape;

public class Application {

    public static void main(String[] args) {

        int length = 5;
        int breadth = 10;
        ShapeFactory shapeCalculator = new ShapeFactory();
        Shape shapeFactory  = shapeCalculator.getShapeFactory("Rectangle");
        System.out.println("Square area: " + shapeFactory.getArea(length, breadth));
        Shape shapeFactory1 = shapeCalculator.getShapeFactory("Square");
        System.out.println("Rectangle area: " + shapeFactory1.getArea(length, breadth));
        System.out.println();

        // Transport factory
        TransportService transportService = new TransportService();
        transportService.send("air");
        transportService.send("water");
    }

}