package com.java.objectCreationPatterns.abstractFactory;

public class Paypal implements PaymentGateway {

    @Override
    public void processPayment(java.math.BigDecimal amount) {
        System.out.println("Processing payment of " + amount + " through Paypal");
    }

}
