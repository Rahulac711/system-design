package com.java.behavioralDesignPattern.statePattern.problemToSolve;

/*
Real-Life Analogy
Consider a food delivery app. As an order progresses, its state changes through multiple stages:
The order is placed.
The order is being prepared.
A delivery partner is assigned.
The order is picked up.
The order is out for delivery.
Finally, the order is delivered.

At each stage, the app behaves differently:
In the "Order Placed" state, you can cancel the order.
In the "Order Preparing" state, you can track the preparation status.
In the "Delivery Partner Assigned" state, you can see the details of the assigned driver.
And so on until the order is delivered.

Each of these states represents a distinct phase, and the app's behavior changes based on which state the order is in.
The State Pattern manages these transitions seamlessly, with each state class controlling the behavior for that phase.
 It also follows Open Closed Principle (OCP), as states can be added without modifying the existing code.

Let's now understand the working of State Pattern through the help of a problem statement.
Understanding The Problem
Let's assume we are building a food delivery app, and we need to manage the different states of an order. The order
 can transition between multiple states, such as placed, preparing, out for delivery, and delivered.

Below is a simplified version of how we might implement this without using the State Pattern:
*/

class Order {
    private String state;

    // Constructor initializes the state to ORDER_PLACED
    public Order() {
        this.state = "ORDER_PLACED";
    }

    // Method to cancel the order 
    // only allows cancellation if in ORDER_PLACED or PREPARING states
    public void cancelOrder() {
        if (state.equals("ORDER_PLACED") || state.equals("PREPARING")) {
            state = "CANCELLED";
            System.out.println("Order has been cancelled.");
        } else {
            System.out.println("Cannot cancel the order now.");
        }
    }

    // Method to move the order to the next state based on its current state
    public void nextState() {
        switch (state) {
            case "ORDER_PLACED":
                state = "PREPARING";
                break;
            case "PREPARING":
                state = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                state = "DELIVERED";
                break;
            default:
                System.out.println("No next state from: " + state);
                return;
        }
        System.out.println("Order moved to: " + state);
    }

    // Getter for the state
    public String getState() {
        return state;
    }
}

class Main {
    // Main method to test the order flow
    public static void main(String[] args) {
        Order order = new Order();
        
        // Display initial state
        System.out.println("Initial State: " + order.getState());

        // Moving through states
        order.nextState(); // ORDER_PLACED -> PREPARING
        order.nextState(); // PREPARING -> OUT_FOR_DELIVERY
        order.nextState(); // OUT_FOR_DELIVERY -> DELIVERED

        // Attempting to cancel an order after it is out for delivery
        order.cancelOrder(); // Should not allow cancellation

        // Display final state
        System.out.println("Final State: " + order.getState());
    }
}
/*

The above code works fine, but there are some critical issues in the code.
Issues In The Code
State Transition Management:
The state transitions are hardcoded in the nextState() method using a switch statement. This approach becomes
cumbersome if new states need to be added.
Lack of Encapsulation:
The state transition logic and cancel behavior are directly handled within the Order class. This violates the
Single Responsibility Principle by combining multiple responsibilities within a single class.
Code Duplication:
The logic for the cancelOrder() and nextState() methods could lead to duplicate logic if more states and actions
are added.
Missing Flexibility for Future Changes:
Adding new states or changing existing behaviors can be error-prone and cumbersome, as the Order class needs to be
updated each time.
*/