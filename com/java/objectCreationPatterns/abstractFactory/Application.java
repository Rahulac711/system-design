package com.java.objectCreationPatterns.abstractFactory;

public class Application {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.checkout(new UsRegion(), "paypal", 1000);
        checkoutService.checkout(new IndiaRegion(), "razorpay", 1000);
    }
}
