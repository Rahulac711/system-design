package com.java.behavioralDesignPattern.visitorPattern.problemToSolve;

import java.util.*;

/*
Understanding the Problem
Let's assume we are building the checkout page of an e-commerce website like Amazon. The checkout process
involves various product types such as physical products, gift cards, and digital products. For each
product, we need to perform specific operations like calculating shipping costs, discounts, and printing
invoices.

Here's a simple way to implement the above requirement:
*/


// Class representing a Physical Product
class PhysicalProduct {
    // Method to print invoice for physical product
    void printInvoice() {
        System.out.println("Printing invoice for Physical Product...");
    }

    // Method to calculate shipping cost for physical product
    double calculateShippingCost() {
        System.out.println("Calculating shipping cost for Physical Product...");
        return 10.0; // Example shipping cost
    }
}

// Class representing a Digital Product
class DigitalProduct {
    // Method to print invoice for digital product
    void printInvoice() {
        System.out.println("Printing invoice for Digital Product...");
    }

    // No shipping cost for digital product
}

// Class representing a Gift Card Product
class GiftCard {
    // Method to print invoice for gift card
    void printInvoice() {
        System.out.println("Printing invoice for Gift Card...");
    }

    // Method to calculate discount for gift card
    double calculateDiscount() {
        System.out.println("Calculating discount for Gift Card...");
        return 5.0; // Example discount
    }
}

class Main {
    public static void main(String[] args) {
        // Create instances of different products
        List<Object> cart = Arrays.asList(new PhysicalProduct(), new DigitalProduct(), new GiftCard());

        // Loop through cart and perform actions based on product type
        for (Object item : cart) {
            if (item instanceof PhysicalProduct) {
                PhysicalProduct physicalProduct = (PhysicalProduct) item;
                physicalProduct.printInvoice();
                double shippingCost = physicalProduct.calculateShippingCost();
                System.out.println("Shipping cost: " + shippingCost + "\n");
            } 
            else if (item instanceof DigitalProduct) {
                DigitalProduct digitalProduct = (DigitalProduct) item;
                digitalProduct.printInvoice();
                System.out.println("No shipping cost for Digital Product." + "\n");
            } 
            else if (item instanceof GiftCard) {
                GiftCard giftCard = (GiftCard) item;
                giftCard.printInvoice();
                double discount = giftCard.calculateDiscount();
                System.out.println("Discount applied: " + discount + "\n");
            }
        }
    }
}
/*
Issues with the Code
Doesn't Follow Single Responsibility Principle (SRP):
The classes PhysicalProduct, DigitalProduct, and GiftCard contain both business logic and actions that
should ideally be in separate classes. For example, printing invoices and calculating shipping costs or
discounts are two separate responsibilities, but they're tightly coupled inside the same class.
Product Type Checking in the Client Code:
In the client code, we are performing checks like instanceof PhysicalProduct, instanceof DigitalProduct,
and so on. This violates the Open-Closed Principle (OCP), as adding a new product type would require
modifying this client code. Ideally, we want to avoid such checks by delegating the operation logic to the
product classes themselves.

Lack of Flexibility:
Adding a new product type in the future (say, SubscriptionProduct) would require modifying the Client Code,
which is not ideal for scalability. Every time a new product is added, the client code would have to be
changed to account for new logic.

Tight Coupling:
The product classes are tightly coupled to the specific operations (printing invoices, calculating shipping
costs, and applying discounts). If you want to add more operations, you'd have to modify each product class,
which can lead to a codebase that's hard to maintain.

*/
