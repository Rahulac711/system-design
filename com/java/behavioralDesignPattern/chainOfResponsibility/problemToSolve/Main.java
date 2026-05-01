package com.java.behavioralDesignPattern.chainOfResponsibility.problemToSolve;

import java.util.*;

// SupportService class: Handles different types of support requests
class SupportService {

    // Method to handle the support request based on the type of issue
    public void handleRequest(String type) {
        if (type.equals("general")) {
            System.out.println("Handled by General Support");
        } else if (type.equals("refund")) {
            System.out.println("Handled by Billing Team");
        } else if (type.equals("technical")) {
            System.out.println("Handled by Technical Support");
        } else if (type.equals("delivery")) {
            System.out.println("Handled by Delivery Team");
        } else {
            System.out.println("No handler available");
        }
    }
}

// Main class: Entry point to test the chain of responsibility pattern
public class Main {

    public static void main(String[] args) {
        // Create an instance of SupportService
        SupportService supportService = new SupportService();
        
        // Test with different types of requests
        supportService.handleRequest("general");
        supportService.handleRequest("refund");
        supportService.handleRequest("technical");
        supportService.handleRequest("delivery");
        supportService.handleRequest("unknown");
    }
}
/*
Issues in the Above Code
Issue	Description
Violation of the Open-Closed Principle:	Every time a new type of request is added, the handleRequest method must
be modified, violating the Open-Closed Principle, which states that a class should be open for extension but closed
for modification.
Monolithic Code:	All logic is contained within a single method, making it difficult to maintain, test, and extend
 the system. Each handler (support team) is tightly coupled with the others.
Scalability and Flexibility:	As the number of support teams grows, we cannot change the order of processing without
 modifying the core logic. It makes adding new handlers or changing the order of requests cumbersome.
 */