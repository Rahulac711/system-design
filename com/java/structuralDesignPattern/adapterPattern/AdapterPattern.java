package com.java.structuralDesignPattern.adapterPattern;

/*
Structural design patterns are concerned with the composition of classes and objects. They focus on
how to assemble classes and objects into larger structures while keeping these structures flexible and
efficient. Adapter Pattern is one of the most important structural design patterns. Let's understand in depth.

{@code Adapter Pattern}
The Adapter Pattern allows incompatible interfaces to work together by acting as a translator or wrapper around
an existing class. It converts the interface of a class into another interface that a client expects.

It acts as a bridge between the Target interface (expected by the client) and the Adaptee (an existing class
with a different interface). This structural wrapping enables integration and compatibility across diverse
systems.
 */

import java.util.Random;

interface PaymentGateway {
    void pay(double amount);
}

class Payu implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Processing payment of " + amount + " through PayU");
    }
}

class RazorpayApi {
    public void makePayment(double amount) {
        Random random = new Random();
        System.out.println("Processing payment of " + amount + " through Razorpay");
    }
}


class RazopayAdapter implements PaymentGateway {

    private RazorpayApi razorpayApi;

    public RazopayAdapter() {
        this.razorpayApi = new RazorpayApi();
    }

    @Override
    public void pay(double amount) {
        razorpayApi.makePayment(amount);
    }
}

class CheckoutService {
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(double amount) {
        paymentGateway.pay(amount);
        System.out.println("Payment paid");
    }
}

class Main {

    public static void main(String args[]) {
        CheckoutService checkoutService = new CheckoutService(new RazopayAdapter());
        checkoutService.checkout(123);
    }

}

/*
Pros:
Code Reusability: Encourages the reuse of existing classes without changing their implementation.
Code Extensibility: Makes systems more flexible and adaptable to change.
Minimal Changes to Client Code: Enables integration without requiring modifications to existing client logic.
Simplifies Third-party Integration: Makes it easier to incorporate external services and APIs.
 */
