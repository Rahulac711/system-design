package com.java.objectCreationPatterns.abstractFactory;

public class PaymentGatewayFactory {

    public static PaymentGateway getPaymentGateway(String type) {
        return switch (type.toLowerCase()) {
            case "razorpay" -> new RazorPay();
            case "payu" -> new PayU();
            default -> throw new IllegalArgumentException("Unknown payment gateway type: " + type);
        };
    }
}
