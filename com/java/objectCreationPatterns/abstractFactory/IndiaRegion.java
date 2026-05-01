package com.java.objectCreationPatterns.abstractFactory;

public class IndiaRegion implements RegionFactory {

    @Override
    public PaymentGateway getPaymentGateWay(String type) {
        return switch (type) {
            case "payu" -> new PayU();
            case "razorpay" -> new RazorPay();
            default -> throw new IllegalArgumentException("Unknown Payment Gateway Type: " + type);
        };
    }

    @Override
    public void generateInvoice() {
        Invoice invoice = new GSTInvoice();
        invoice.generateInvoice();
    }

}
