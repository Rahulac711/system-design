package com.java.structuralDesignPattern.compositePattern;

import java.util.ArrayList;
import java.util.List;

/*
Composite Pattern
The Composite Pattern is a structural design pattern that allows you to compose objects into tree
structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions
of objects uniformly.
Problem It Solves
The Composite Pattern solves the problem of treating individual objects and groups of objects in the same
way. The main problem arises when:
You want to work with a hierarchy of objects.
You want the client code to be agnostic to whether it's dealing with a single object or a collection of them.
*/
interface CartItem {
    void display(String indent);
    double getPrice();
}

class Product implements CartItem {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Product: " + name + " - ₹" + price);
    }

    @Override
    public double getPrice() {
        return price;
    }

}

class ProductBundle implements CartItem {
    String name;
    List<CartItem> productList;

    ProductBundle(String name) {
        this.name = name;
        productList = new ArrayList<>();
    }

    void add(CartItem cartItem) {
        productList.add(cartItem);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "\nBundle " + name);
        for(CartItem cartItem : productList) {
            cartItem.display("          ");
        }
    }

    @Override
    public double getPrice() {
        double total = 0;
        for(CartItem cartItem : productList){
            total += cartItem.getPrice();
        }
        return total;
    }
}

public class CompositePattern {
    public static void main(String args[]) {
        CartItem iphone = new Product("Iphone", 1000);
        CartItem airPod = new Product("Airpod", 800);
        CartItem mac = new Product("Mac Book", 3000);

        ProductBundle schoolKit = new ProductBundle("School kit");
        schoolKit.add(new Product("Book", 10));
        schoolKit.add(new Product("Pen", 2));
        schoolKit.add(new Product("Bag", 20));

        ProductBundle applekit = new ProductBundle("Apple kit");
        applekit.add(iphone);
        applekit.add(airPod);
        applekit.add(mac);

        schoolKit.display("Price of kit: " + schoolKit.getPrice());
        applekit.display("Price of kit: " + applekit.getPrice());
    }
}
